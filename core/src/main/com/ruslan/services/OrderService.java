package com.ruslan.services;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.database.DAO.IOrderDao;
import com.ruslan.database.DAO.IRequestDao;
import com.ruslan.database.DAO.OrderRepository;
import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.book.BookStatus;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.order.OrderStatus;
import com.ruslan.entity.request.Request;
import com.ruslan.json.JsonReader;
import com.ruslan.json.JsonWriter;
import com.ruslan.services.sinterface.IOrderService;
import com.ruslan.services.sinterface.IRequestService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService implements IOrderService {
    private final Logger logger = LogManager.getLogger();
    private final String fileName = "Orders.csv";

    public final String pathOrdersJSON = "src\\main\\resources\\Orders.json";


    @Inject
    private IRequestService requestService;
    @Inject
    private IOrderDao orderDao;
    @Inject
    private IRequestDao requestDao;
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private RequestRepository requestRepository;
    public OrderService() {
    }

    @Override
    public Order createOrder(List<Book> listBooks) {
        Order ord = new Order(listBooks);
        orderRepository.save(ord);
        System.out.println("Order created with id=" + ord.getId() + " contains " + listBooks.size() + " books");
        listBooks.stream()
                .filter(book -> book.getStatus() == BookStatus.OUT_OF_STOCK)
                .forEach(book -> requestRepository.save(new Request(book)));
        return ord;
    }

    public void changeOrderDateCreated(int orderId, LocalDate date) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setDateCreated(date);
            orderRepository.update(order);
        });
    }

    public void changeOrderDateExecution(int orderId, LocalDate date) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setDateExecution(date);
            orderRepository.update(order);
        });
    }

    // List of orders (sort by date of execution, by price, by status);
    public List<Order> getOrdersSortedByDateExecution() {
        //   List<Order> orderList = orderDao.getOrdersList();
        List<Order> orderList = orderRepository.findAll();
        orderList.sort(Comparator.comparing(Order::getDateExecution,
                Comparator.nullsLast(Comparator.naturalOrder())));
        return orderList;
    }

    public List<Order> getOrdersSortedByPrice() {

        List<Order> orderList = orderRepository.findAll();
        orderList.sort(Comparator.comparing(Order::getTotalPrice));
        return orderList;
    }

    public List<Order> getOrdersSortedByStatus() {
        List<Order> orderList = orderRepository.findAll();
        orderList.sort(Comparator.comparing(Order::getStatus));
        return orderList;
    }


    public void changeOrderStatus(int orderId, OrderStatus newOrderStatus) {
        orderRepository.findById(orderId).ifPresent(order -> {
            if (newOrderStatus == OrderStatus.COMPLETED) {
                order.getListBook().stream().filter(book ->
                                //requestDao.findRequestByBookId(book.getId()).orElse(null) != null)
                requestDao.findRequestByBookId(book.getId()).orElse(null) != null)
                        .findAny().ifPresentOrElse(book -> {
                            System.out.println("Request with id="
                                    + requestDao.findRequestByBookId(book.getId()).orElse(null)
                                    + " is not finished. Please close request");
                        }, () -> {
                            order.setStatus(newOrderStatus);
                            order.setDateExecution(LocalDate.now());
                            orderRepository.update(order);
                            System.out.println("Status for Order with id=" + orderId + " changed: " + newOrderStatus);
                        });
            } else {
                order.setStatus(newOrderStatus);
                orderRepository.update(order);
                System.out.println("Status for Order with id=" + orderId + " changed: " + newOrderStatus);
            }
        });
    }


    @Override
    public void removeOrder(int orderId) throws SQLException {
        orderDao.removeById(orderId);
    }


    //Order details (any customer data + books);
    public Order OrderDetails(int orderId) {
        return orderDao.findById(orderId).orElse(null);
    }

    // The number of completed orders over a period of time;
    public int getCountCompletedOrdersForPeriod(LocalDate date1, LocalDate date2) {
        return orderDao.getCompletedOrdersForPeriod(date1, date2).size();
    }

    // The amount of money earned over a period of time;
    public Integer getEarnedMoneyForPeriod(LocalDate date1, LocalDate date2) {
        return orderDao.getCompletedOrdersForPeriod(date1, date2)
                .stream().mapToInt(Order::getTotalPrice).sum();
    }

    // List of completed orders for a period of time (sort by date, by price);
    public List<Order> getCompletedOrderSortedByDateForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = orderDao.getCompletedOrdersForPeriod(date1, date2);
        orderList.sort(Comparator.comparing(Order::getDateCreated));
        return orderList;
    }

    public List<Order> getCompletedOrdersSortedByPriceForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = orderDao.getCompletedOrdersForPeriod(date1, date2);
        orderList.sort(Comparator.comparing(Order::getTotalPrice));
        return orderList;
    }

    public void writeOrderToFile(int id) {
        File orderFile = new File(fileName);
        FileOutputStream fos;
        ObjectOutputStream oos;
        List<Order> orderList;

        try {
            orderFile.createNewFile(); // if file already exists will do nothing
            orderList = getOrderListFromFile();
            orderList.add(orderDao.findById(id).orElse(null));

            fos = new FileOutputStream(orderFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(orderList);
            oos.close();
        } catch (NullPointerException e) {
            System.out.println("Could not get data from file.");
            logger.error("Could not get data to file", e);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please repeat operation");
            logger.error("File not found", e);
        } catch (IOException e) {
            System.out.println("Could not write data to file");
            logger.error("Could not write data to file", e);
        }
    }


    public List<Order> getOrderListFromFile() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Order> orderList;

        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            orderList = (List<Order>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class 'Order' not found!");
            logger.error("Class 'Order' not found!", e);
            return null;
        } catch (EOFException e) {
            System.out.println("File Orders.csv was empty");
            return orderList = new ArrayList<>();
        } catch (StreamCorruptedException e) {
            System.out.println("Uncorrected data in file. Data was erased");
            return orderList = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Could not read data from file");
            logger.error("Could not read data from file", e);
            return null;
        }
        return orderList;
    }


    public Order getOrderFromFile(int id) {
        return getOrderListFromFile()
                .stream()
                .filter(order ->
                        order.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void importOrdersFromJson() {
        List<Order> orderList = jsonReader.readEntities(Order.class, pathOrdersJSON);
        orderList.forEach(order -> orderDao.saveOrder(order));
    }

    public void exportOrdersToJson() {
        jsonWriter.writeEntities(orderDao.getOrdersList(), pathOrdersJSON);
    }
}