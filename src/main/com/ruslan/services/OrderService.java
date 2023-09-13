package com.ruslan.services;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.jsonHandlers.JsonReader;
import com.ruslan.jsonHandlers.JsonWriter;
import com.ruslan.services.sinterface.IOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderService implements IOrderService {
    private static final Logger logger = LogManager.getLogger();
    private static final String fileName = "Orders.csv";

    public static final String pathOrdersJSON = "src\\main\\resources\\Orders.json";
    private final RequestRepository requestRepository;
    private final OrderRepository orderRepository;

    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();

    public OrderService(OrderRepository orderRepository, RequestRepository requestRepository) {
        this.orderRepository = orderRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public Order createOrder(List<Book> listBooks) {
        Order ord = new Order(listBooks);
        orderRepository.saveOrder(ord);
        System.out.println("Order created with id=" + ord.getId() + " contains " + listBooks.size() + " books");
        listBooks.stream()
                .filter(book -> book.getStatus() == BookStatus.OUT_OF_STOCK)
                .forEach(requestRepository::createRequest);
        return ord;
    }

    public void changeOrderDateCreated(int orderId, LocalDate date) {
        orderRepository.getById(orderId).ifPresent(order -> order.setDateCreated(date));
    }

    public void changeOrderDateExecution(int orderId, LocalDate date) {
        orderRepository.getById(orderId).ifPresent(order -> order.setDateExecution(date));
    }

    // List of orders (sort by date of execution, by price, by status);
    public List<Order> getOrdersSortedByDateExecution() {
        List<Order> orderList = orderRepository.getOrdersList();
        orderList.sort(Comparator.comparing(Order::getDateExecution,
                Comparator.nullsLast(Comparator.naturalOrder())));
        return orderList;
    }

    public List<Order> getOrdersSortedByPrice() {
        List<Order> orderList = orderRepository.getOrdersList();
        orderList.sort(Comparator.comparing(Order::getTotalPrice));
        return orderList;
    }

    public List<Order> getOrdersSortedByStatus() {
        List<Order> orderList = orderRepository.getOrdersList();
        orderList.sort(Comparator.comparing(Order::getStatus));
        return orderList;
    }


    public void changeOrderStatus(int orderId, OrderStatus newOrderStatus) {
        orderRepository.getById(orderId).ifPresent(order -> {
            if (newOrderStatus == OrderStatus.COMPLETED) {
                order.getListBook().stream().filter(book ->
                                requestRepository.getRequestForBook(book.getId()) != null)
                        .findAny().ifPresentOrElse(book -> {
                            System.out.println("Request for book with id="
                                    + requestRepository.getRequestForBook(book.getId()).getBook().getId()
                                    + " is not finished. Please close request");
                        }, () -> {
                            orderRepository.updateStatus(orderId, newOrderStatus);
                            orderRepository.setDateExecution(orderId, LocalDate.now());
                            System.out.println("Status for Order with id=" + orderId + " changed: " + newOrderStatus);
                        });
            } else {
                orderRepository.updateStatus(orderId, newOrderStatus);
                System.out.println("Status for Order with id=" + orderId + " changed: " + newOrderStatus);
            }
        });
    }


    @Override
    public void removeOrder(int orderId) {
        orderRepository.removeById(orderId);
    }

    //Order details (any customer data + books);
    public Order OrderDetails(int orderId) {
        return orderRepository.getById(orderId).orElse(null);
    }

    // The number of completed orders over a period of time;
    public int getCountCompletedOrdersForPeriod(LocalDate date1, LocalDate date2) {
        return orderRepository.getCompletedOrdersForPeriod(date1, date2).size();
    }

    // The amount of money earned over a period of time;
    public Integer getEarnedMoneyForPeriod(LocalDate date1, LocalDate date2) {
        return orderRepository.getCompletedOrdersForPeriod(date1, date2)
                .stream()
                .map(Order::getTotalPrice)
                .reduce(0, Integer::sum);
    }

    // List of completed orders for a period of time (sort by date, by price);
    public List<Order> getCompletedOrdersSortedByDateForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = orderRepository.getCompletedOrdersForPeriod(date1, date2);
        orderList.sort(Comparator.comparing(Order::getDateCreated));
        return orderList;
    }

    public List<Order> getCompletedOrdersSortedByPriceForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderList = orderRepository.getCompletedOrdersForPeriod(date1, date2);
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
            orderList.add(OrderRepository.getInstance().getById(id).orElse(null));

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
        orderList.forEach(order -> orderRepository.addOrder(order.getId(), order));
    }


    public void exportOrdersToJson() {
        jsonWriter.writeEntities(orderRepository.getOrdersList(), pathOrdersJSON);
    }
}
