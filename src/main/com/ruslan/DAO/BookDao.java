package com.ruslan.DAO;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.JDBC.ConnectionManager;
import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import exception.DaoException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDao implements IBookDao {
    @Inject
    ConnectionManager connectionManager;



    private final String deleteBook = """
            DELETE FROM book
            WHERE id = ?
            """;
    private final String saveBook = """
            INSERT INTO book (`Title`, `Author`, `Price`,`Status`,	`Description`, `DatePublication` )
            VALUE ( ?,?,?,?,?,?)
            """;

    private final String updateBook = """
            UPDATE book
            SET title = ?,
                author=?,
                price=?,
                status=?,
                description=?,
                satePublication=?
            WHERE id=?;
            """;
    private final String findById = """
                  SELECT  id,
                          title,
                          author,
                          price,
                          status,
                          description,
                          datePublication
                  FROM Book
                  WHERE id= ?
            """;
    private final String getAllBooks = """
            SELECT  id,
                    title,
                    author,
                    price,
                    status,
                    description,
                    datePublication
            FROM Book
                 """;

    public BookDao() {
    }

    @Override
    public Optional<Book> findById(int id) {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Book book = null;
            if (resultSet.next()) {
                book = buildBook(resultSet);
            }
            return Optional.ofNullable(book);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Book saveBook(Book book) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(saveBook, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(book.getPrice()));
            preparedStatement.setString(4, String.valueOf(book.getStatus()));
            preparedStatement.setString(5, book.getDescription());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(book.getDatePublication().atStartOfDay()));
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            return book;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void update(Book book) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateBook);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBigDecimal(3, BigDecimal.valueOf(book.getPrice()));
            preparedStatement.setString(4, String.valueOf(book.getStatus()));
            preparedStatement.setString(5, book.getDescription());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(book.getDatePublication().atStartOfDay()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Boolean removeById(int idBook) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteBook);
            preparedStatement.setInt(1, idBook);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Book> getBooksList() {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(getAllBooks);

            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }

            return books;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private Book buildBook(ResultSet resultSet) {
        try {
            return new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getBigDecimal(4).intValue(),
                    BookStatus.valueOf(resultSet.getString(5)),
                    resultSet.getString(6),
                    resultSet.getDate(7).toLocalDate()
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
