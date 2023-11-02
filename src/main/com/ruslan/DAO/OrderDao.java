package com.ruslan.DAO;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.JDBC.ConnectionManager;
import com.ruslan.data.book.Book;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import exception.DaoException;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDao implements IOrderDao {
    @Inject
    ConnectionManager connectionManager;
    @Inject
    IBookDao bookDao;

    private final String deleteOrder = """
            DELETE FROM `order`
            WHERE id = ?
            """;
    private final String deleteOrderFromBooksOrder = """
            DELETE FROM `booksOrder`
            WHERE orderId = ?
            """;
    private final String saveOrder = """
            INSERT INTO `order` ( `buyer`, `address`, `status`, `totalPrice`, `dateCreated`, `dateExecution` )
            VALUE ( ?,?,?,?,?,?)
            """;
    private final String updateOrder = """
            UPDATE `order`
            SET  
                buyer = ?,
                address=?,
                status=?,
                totalPrice=?,
                dateCreated=?,
                dateExecution=?
            WHERE id=?;
            """;

    private final String findById = """
                  SELECT  id,
                          buyer,
                          address,
                          status,
                          totalPrice,
                          dateCreated,
                          dateExecution                
                  FROM `order`
                  WHERE id= ?
            """;

    private final String getAllOrders = """
            SELECT  id,
                    buyer,
                    address,
                    status,
                    totalPrice,
                    dateCreated,
                    dateExecution
            FROM `order`
                 """;
    private final String saveBooksListForOrder = """
            INSERT INTO booksOrder ( `BookId`, `OrderId` )
                        VALUE ( ?,?)
                 """;
    private final String findBooksForOrder = """
                  SELECT  bookId
                  FROM booksOrder
                  WHERE orderId= ?
            """;
    private final String getCompletedOrdersForPeriod = """
            SELECT  id,
                    buyer,
                    address,
                    status,
                    totalPrice,
                    dateCreated,
                    dateExecution
            FROM `order`
            where status = 'COMPLETED' AND dateExecution BETWEEN ? AND ?
                 """;

    public OrderDao() {
    }

    @Override
    public Optional<Order> findById(int id) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(findById);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }


    private List<Book> buildBooksList(int idOrder) {

        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(findBooksForOrder);
            preparedStatement.setInt(1, idOrder);
            var resultSet = preparedStatement.executeQuery();
            List<Book> listBooks = new ArrayList<>();
            if (resultSet.next()) {
                listBooks.add(bookDao.findById(resultSet.getInt(1)).orElse(null));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order saveOrder(Order order) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(saveOrder, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, order.getBuyer());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, String.valueOf(order.getStatus()));
            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(order.getTotalPrice()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(order.getDateCreated().atStartOfDay()));
            if (order.getDateExecution() != null) {
                preparedStatement.setTimestamp(6, Timestamp.valueOf(order.getDateExecution().atStartOfDay()));
            } else {
                preparedStatement.setTimestamp(6, null);
            }
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }
            for (int i = 0; i < order.getListBook().size(); i++) {
                PreparedStatement preparedStatementTwo = connection.prepareStatement(saveBooksListForOrder);
                preparedStatementTwo.setInt(1, order.getListBook().get(i).getId());
                preparedStatementTwo.setInt(2, order.getId());
                preparedStatementTwo.executeUpdate();
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Order order) {
        try (var connection = connectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(updateOrder);
            preparedStatement.setString(1, order.getBuyer());
            preparedStatement.setString(2, order.getAddress());
            preparedStatement.setString(3, String.valueOf(order.getStatus()));
            preparedStatement.setBigDecimal(4, BigDecimal.valueOf(order.getTotalPrice()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(order.getDateCreated().atStartOfDay()));
            if (order.getDateExecution() != null) {
                preparedStatement.setTimestamp(6, Timestamp.valueOf(order.getDateExecution().atStartOfDay()));
            } else {
                preparedStatement.setTimestamp(6, null);
            }
            preparedStatement.setInt(7, order.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Boolean removeById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement deleteOrderFromTableBooksOrder = null;
        PreparedStatement deleteOrderFromTableOrder = null;
        try {
            connection = connectionManager.get();
            deleteOrderFromTableBooksOrder = connection.prepareStatement(deleteOrderFromBooksOrder);
            deleteOrderFromTableOrder = connection.prepareStatement(deleteOrder);
            connection.setAutoCommit(false);
            deleteOrderFromTableBooksOrder.setInt(1, id);
            deleteOrderFromTableOrder.setInt(1, id);
            deleteOrderFromTableBooksOrder.executeUpdate();
            deleteOrderFromTableOrder.executeUpdate();
            return deleteOrderFromTableOrder.executeUpdate() > 0;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
            if (deleteOrderFromTableBooksOrder != null) {
                deleteOrderFromTableBooksOrder.close();
            }
            if (deleteOrderFromTableOrder != null) {
                deleteOrderFromTableOrder.close();
            }
        }
    }

    @Override
    public List<Order> getCompletedOrdersForPeriod(LocalDate date1, LocalDate date2) {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(getCompletedOrdersForPeriod);
            preparedStatement.setTimestamp(1, Timestamp.valueOf((date1).atStartOfDay()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf((date1).atStartOfDay()));
            var resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> getOrdersList() {
        try (var connection = connectionManager.get()) {
            var preparedStatement = connection.prepareStatement(getAllOrders);

            var resultSet = preparedStatement.executeQuery();

            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Order buildOrder(ResultSet resultSet) {
        try {
            var dateExecution = resultSet.getDate(7) == null
                    ? null : resultSet.getDate(7).toLocalDate();
            return new Order(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    OrderStatus.valueOf(resultSet.getString(4)),
                    resultSet.getBigDecimal(5).intValue(),
                    resultSet.getDate(6).toLocalDate(),
                    dateExecution,
                    buildBooksList(resultSet.getInt(1))
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}