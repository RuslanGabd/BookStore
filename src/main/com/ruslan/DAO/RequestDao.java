package com.ruslan.DAO;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.JDBC.ConnectionManager;
import com.ruslan.data.book.Book;
import com.ruslan.data.request.Request;
import exception.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestDao implements IRequestDao {
    @Inject
    ConnectionManager connectionManager;
    @Inject
    IBookDao bookDao;

    private final String findById = """
                  SELECT  id,
                          bookId,
                          date                                           
                  FROM Request
                  WHERE id= ?
            """;
    private final String findRequestByBookId = """
                  SELECT  id,
                          bookId,
                          date                                           
                  FROM Request
                  WHERE bookId= ?
            """;
    private final String getAllRequests = """
            SELECT  id,
                    bookId,
                    date                                           
              FROM Request
            """;
    private final String saveRequest = """
            INSERT INTO request (`bookId`, `date` )
            VALUE ( ?,?)
            """;
    private final String deleteRequestByRequestId = """
            DELETE FROM request
            WHERE id = ?
            """;
    private final String deleteRequestByBookId = """
            DELETE FROM request
            WHERE bookId = ?
            """;
    public RequestDao() {
    }

    @Override
    public Optional<Request> findById(int id) {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Request request = null;
            if (resultSet.next()) {
                request = buildRequest(resultSet);
            }
            return Optional.ofNullable(request);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }
    @Override
    public Optional<Request> findRequestByBookId(int id) {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(findRequestByBookId);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Request request = null;
            if (resultSet.next()) {
                request = buildRequest(resultSet);
            }
            return Optional.ofNullable(request);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Request saveRequest(Request request) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(saveRequest, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, request.getBook().getId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(request.getDate().atStartOfDay()));
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                request.setId(generatedKeys.getInt(1));
            }
            return request;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Boolean removeByRequestId(int id) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRequestByRequestId);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Boolean removeByBookId(int id) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteRequestByBookId);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Request> getRequestsList() {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(getAllRequests);
             var resultSet = preparedStatement.executeQuery();
            List<Request> requests = new ArrayList<>();
            while (resultSet.next()) {
                requests.add(buildRequest(resultSet));
            }
            return requests;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private Request buildRequest(ResultSet resultSet) {
        try {
            Book book = bookDao.findById(resultSet.getInt(2)).orElse(null);
            return new Request(
                    resultSet.getInt(1),
                    book,
                    resultSet.getDate(3).toLocalDate()
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}