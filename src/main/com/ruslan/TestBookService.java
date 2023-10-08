package com.ruslan;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruslan.DI.ObjectFactory;
import com.ruslan.DI.context.ApplicationContext;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.ui.MenuController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestBookService {

    public static void main(String[] args) throws IOException, ClassNotFoundException, JsonProcessingException {
//
//        final BookRepository bookRepository =  BookRepository.getInstance();
//        final OrderRepository orderRepository = OrderRepository.getInstance();
//        final RequestRepository requestRepository = RequestRepository.getInstance();
//
//        BookService bookService = new BookService(bookRepository, orderRepository, requestRepository);
//
//        OrderService orderService = new OrderService(orderRepository, requestRepository);
//        RequestService requestService = new RequestService(requestRepository, bookRepository);
//
//
//        Book book1 = bookService.createBook("Finalist", "Teodor D", 100,LocalDate.of(2023, 6, 5));
//        Book book2 = bookService.createBook("Silver", "Jack London", 50, LocalDate.of(2023, 5, 21));
//        Book book3 = bookService.createBook("Chemistry", "Mohjan P.", 180, LocalDate.of(2023, 4, 24));
//        Book book4 = bookService.createBook("King", "Sven Richi", 90, LocalDate.of(2023, 3, 17));
//        Book book5 = bookService.createBook("Wolf", "Tramp K.", 10, LocalDate.of(2023, 2, 11));
//        Book book6 = bookService.createBook("Fox", "Brus Li", 146, LocalDate.of(2023, 1, 5));
//        Book book7 = bookService.createBook("Cat", "Uma Turman", 123, LocalDate.of(2022, 12, 3));
//
//        bookService.exportBooksToJson();
//
//        orderService.createOrder(Arrays.asList(bookRepository.getById(1), bookRepository.getById(2)));
//        orderService.createOrder(Arrays.asList(bookRepository.getById(3), bookRepository.getById(4)));
//        orderService.createOrder(Arrays.asList(bookRepository.getById(4), bookRepository.getById(5),bookRepository.getById(6)));
//        orderService.createOrder(Arrays.asList(bookRepository.getById(4), bookRepository.getById(5),bookRepository.getById(6)));
//        orderService.createOrder(Arrays.asList(bookRepository.getById(1), bookRepository.getById(5),bookRepository.getById(6)));
//        orderService.createOrder(Arrays.asList(bookRepository.getById(3), bookRepository.getById(5),bookRepository.getById(6)));
//
//        orderService.changeOrderDateCreated(1, LocalDate.of(2023, 06, 30));
//        orderService.changeOrderDateCreated(2, LocalDate.of(2023, 05, 30));
//        orderService.changeOrderDateCreated(3, LocalDate.of(2023, 04, 30));
//        orderService.changeOrderDateCreated(5, LocalDate.of(2023, 03, 30));
//        orderService.changeOrderDateCreated(2, LocalDate.of(2023, 01, 30));
//
//        orderService.changeOrderDateExecution(1, LocalDate.of(2023, 06, 30));
//        orderService.changeOrderDateExecution(3, LocalDate.of(2023, 06, 30));
//        orderService.changeOrderDateExecution(4, LocalDate.of(2023, 06, 30));
//        orderService.changeOrderDateExecution(5, LocalDate.of(2023, 06, 30));
//
//        requestService.createRequest(5);
//        requestService.createRequest(1) ;
//
//        orderService.changeOrderStatus(1, OrderStatus.COMPLETED);
//        orderService.changeOrderStatus(3, OrderStatus.COMPLETED);
//        orderService.changeOrderStatus(4, OrderStatus.COMPLETED);
//        orderService.changeOrderStatus(5, OrderStatus.COMPLETED);
//        orderService.changeOrderStatus(2, OrderStatus.CANCELLED);
//        List of books (sort alphabetically, by date of publication, by price, by stock availability);
//        bookService.printList("Books sorted by Title Alphabetically:", bookService.getBooksSortedByTitleAlphabetically());
//        bookService.printList("Books sorted by DAte Publication:", bookService.getBooksSortedByDatePublication());
//        bookService.printList("Books sorted by Status:", bookService.getBooksSortedByStatus());
//
//
//        List of orders (sort by date of execution, by price, by status);
//        orderService.printList("Orders sorted by Price:", orderService.getOrdersSortedByPrice());
//        orderService.printList("Orders sorted by Status:", orderService.getOrdersSortedByStatus());
//        orderService.printList("Orders sorted by DateExecution:", orderService.getOrdersSortedByDateExecution());
//
//        //List of book requests (sort by number of requests, alphabetically);
//        requestService.printList("Requests sorted by Number:", requestService.getRequestSortedByNumber());
//        requestService.printList("Requests sorted by Alphabetically", requestService.getRequestSortedByAlphabetically());
//
//
//        List of completed orders for a period of time (sort by date, by price);
//           orderService.printList("Orders for period sorted by Date", orderService.getOrdersSortedByDateForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
//          orderService.printList("Orders for period sorted by Price", orderService.getOrdersSortedByPriceForPeriod(LocalDate.of(1970, 1, 1),
//                 LocalDate.of(2023, 06, 30)));
//
//         The amount of money earned over a period of time;
//        System.out.println("Earned money for period: "
//                + orderService.getEarnedMoneyForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
//
//
//        //The number of completed orders over a period of time;
//        System.out.println("Total completed orders over a period of time: "
//                + orderService.getCountCompletedOrdersForPeriod(LocalDate.of(1970, 1, 1),
//                LocalDate.of(2023, 06, 30)));
////
////        //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);
////        bookService.printList("Stale books sorted by Price", bookService.getStaleBooksSortedByPrice());
////        bookService.printList("Stale books sorted by Date", bookService.getStaleBooksSortedByDate());
//
//        //Order details (any customer data + books);
//        System.out.println(  orderService.OrderDetails(5));
//        //Description of the book.
//        bookService.getDescriptionOfBook(6);
//        System.out.println(requestService.getRequestSortedByAlphabetically());
//        Gson gson =
//                new GsonBuilder()
//                        .registerTypeAdapter( Instant.class , new GsonInstantTypeAdapter() )
//                        .create();
//
//         Write to JSON.
//       l Person person = new Person( "John Doe" , Instant.now() ); //For example: 2022-02-02T11:11:510Z
//        String personAsJson = gson.toJson( person );
//
//          System.out.println( "personAsJson = " + personAsJson );
//
//         Parse JSON.
//        Person p = gson.fromJson( personAsJson , Person.class );
//
//           System.out.println( "p.toString() = " + p );
//          new Gson().toJson(BookRepository.getInstance());
//        JsonReader jsonReader;
//        JsonWriter jsonWriter = new JsonWriter();
//        jsonWriter.writeEntities(bookRepository.getBooksList(),  "src\\main\\resources\\craftsmen.json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jacksonData = objectMapper.writeValueAsString(bookRepository.getBooksList());
//        System.out.println( jacksonData);
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
//        writer.writeValue(new File("dataTwo.json"), jacksonData);
        ApplicationContext applicationContext = new ApplicationContext();
        ObjectFactory objectFactory = new ObjectFactory(applicationContext, new HashMap<>());
        applicationContext.setObjectFactory(objectFactory);
        MenuController menuController = applicationContext.getObject(MenuController.class);
        menuController.run();
    }
}
