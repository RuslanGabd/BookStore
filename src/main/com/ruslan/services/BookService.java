package com.ruslan.services;

import com.ruslan.RandomDate;
import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.Repository;
import com.ruslan.data.request.Request;

import java.time.LocalDate;
import java.util.*;

public class BookService {
    private final Repository repository;

    private static final LocalDate MIN_DATE = LocalDate.of(1970, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(2023, 06, 30);

    public BookService(final Repository repository) {
        this.repository = repository;
    }

    public Book createBook(String title, String author, int price, LocalDate datePublication) {
        Book bk = new Book(title, author, price, datePublication);

        repository.getStock().add(bk);
        System.out.println("Created book: " + bk.toString());
        return bk;
    }

    public void changeStatusBook(Book book, BookStatus status) {
        for (Book bk : repository.getStock()) {
            if (bk.equals(book)) {
                bk.setStatus(status);
                System.out.println("Book id=" + book.getId() + " changed status " + status);
            }
        }
    }

    public void addBookToStockAndCancelRequests(Book book) {
        for (Book bk : repository.getStock()) {
            if (book.equals(bk)) {
                bk.setStatus(BookStatus.IN_STOCK);
                cancelRequestsForBook(book);
                System.out.println("Book " + book.getId() + " add to stock");
            }
        }
    }

    public void removeBookFromStock(Book book) {
        for (Book bk : repository.getStock()) {
            if (book.equals(bk)) {
                bk.setStatus(BookStatus.OUT_OF_STOCK);
                System.out.println("Book id=" + book.getId() + " status changed OUT_OF_STOCK");
            }
        }
    }

    public void createOrder(List<Book> totalBooks) {
        Order ord = new Order(totalBooks);
        repository.addOrder(ord);
        System.out.println("Order created with id=" + ord.getId() + " contains " + totalBooks.size() + " books");
        for (Book book : totalBooks) {
            if (book.getStatus() == BookStatus.OUT_OF_STOCK) {
                createRequest(book);
            }
        }
    }

//    public LocalDate randomDateForTest() {
//        long minDay = MIN_DATE.toEpochDay();
//        long maxDay = MAX_DATE.toEpochDay();
//        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
//        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
//        return randomDate;
//    }

    public void changeOrderStatus(int id, OrderStatus status) {
        for (Order ord : repository.getOrders()) {
            if (ord.getId() != id) {
                continue;
            }
            if (status == OrderStatus.FULFILLED) {
                if (repository.getRequests().size() != 0) { //search request for book
                    for (Request rq : repository.getRequests()) {
                        if (ord.getListBook().contains(rq.getBook())) {
                            System.out.println("Request for book id= " + rq.getBook().getId() +
                                    " is not finished. Please close request");
                        }
                    }
                } else


                    repository.changeStatusOrder(id, status);
                repository.addDateExecutionToOrder(id, new RandomDate().generateDateForTest());
                // repository.addDateExecutionToOrder(id, LocalDate.now());
                System.out.println("Status for Order with id=" + id + " changed: " + status);

            } else {
                repository.changeStatusOrder(id, status);
                System.out.println("Status for Order with id=" + id + " changed: " + status);
            }
        }

    }


    public void cancelOrder(int id) {
        for (Iterator<Order> iterator = repository.getOrders().iterator(); iterator.hasNext(); )
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        System.out.println("Order with id=" + id + " was canceled");
    }

    public void cancelRequestsUseId(int id) {
        repository.removeRequest(id);
        System.out.println("Request id=" + id + " canceled");
    }

    public void cancelRequestsForBook(Book book) {
        for (Request req : repository.getRequests())
            if (req.getBook().equals(book)) {
                repository.removeRequest(req.getId());
                System.out.println("Request id=" + req.getId() + " canceled");
            }
    }

    public void createRequest(Book book) {
        Request request = new Request(book);
        repository.addRequest(request);
        System.out.print("New request created with id=" + request.getId());
        System.out.println(" Total requests: " + repository.getRequests().size());
    }

//   List of books (sort alphabetically, by date of publication, by price, by stock availability);

    public void printAllBooksSortedByTitleAlphabetically() {
        Collections.sort(repository.getStock(), (o1, o2) -> CharSequence.compare(o2.getTitle(), o1.getTitle()));
        System.out.println(Arrays.toString(repository.getStock().toArray()));
    }

    public void printAllBooksSortedByDatePublication() {
        Collections.sort(repository.getStock(), Comparator.comparing(Book::getDatePublication));
        System.out.println("Books sorted by DatePublication:" + Arrays.toString(repository.getStock().toArray()));
    }

    public void printAllBooksSortedByPrice() {
        Collections.sort(repository.getStock(), Comparator.comparingInt(Book::getPrice));
        System.out.println(Arrays.toString(repository.getStock().toArray()));
    }

    public void printAllBooksSortedByStatus() {
        List<Book> sortedBooks = repository.getStock();
        Book temp;
        for (Book bk : repository.getStock()) {
            for (int i = 0; i < sortedBooks.size() - 1; i++) {
                if (sortedBooks.get(i).getStatus() == BookStatus.NOT_AVAILABLE &&
                        sortedBooks.get(i + 1).getStatus() == BookStatus.IN_STOCK) {
                    temp = sortedBooks.get(i);
                    sortedBooks.set(i, sortedBooks.get(i + 1));
                    sortedBooks.set(i + 1, temp);
                }
                if (sortedBooks.get(i).getStatus() == BookStatus.NOT_AVAILABLE &&
                        sortedBooks.get(i + 1).getStatus() == BookStatus.OUT_OF_STOCK) {
                    temp = sortedBooks.get(i);
                    sortedBooks.set(i, sortedBooks.get(i + 1));
                    sortedBooks.set(i + 1, temp);
                }
                if (sortedBooks.get(i).getStatus() == BookStatus.OUT_OF_STOCK &&
                        sortedBooks.get(i + 1).getStatus() == BookStatus.IN_STOCK) {
                    temp = sortedBooks.get(i);
                    sortedBooks.set(i, sortedBooks.get(i + 1));
                    sortedBooks.set(i + 1, temp);
                }
            }
        }
        System.out.println("Books sorted by Status:" + Arrays.toString(sortedBooks.toArray()));
    }

    public void printAllBooksSortedByAuthor() {
        Collections.sort(repository.getStock(), (o1, o2) -> CharSequence.compare(o2.getAuthor(), o1.getAuthor()));
        System.out.println(Arrays.toString(repository.getStock().toArray()));
    }


    //List of orders (sort by date of execution, by price, by status);
    public void printAllOrdersSortedByDateExecution() {
        Collections.sort(repository.getOrders(), Comparator.comparing(Order::getDateExecution,
                Comparator.nullsLast(Comparator.naturalOrder())));
        System.out.println("Orders sorted by DateExecution:" + Arrays.toString(repository.getOrders().toArray()));
    }

    public void printAllOrdersSortedByPrice() {
        Collections.sort(repository.getOrders(), Comparator.comparing(Order::getTotalPrice));
        System.out.println("Order sorted by Price:" + repository.getOrders());
    }

    public void printAllOrdersSortedByStatus() {
        List<Order> sortedOrders = repository.getOrders();
        Order temp;
        for (Order ord : repository.getOrders()) {
            for (int i = 0; i < sortedOrders.size() - 1; i++) {
                if (sortedOrders.get(i).getStatus() == OrderStatus.FULFILLED &&
                        sortedOrders.get(i + 1).getStatus() == OrderStatus.NEW) {
                    temp = sortedOrders.get(i);
                    sortedOrders.set(i, sortedOrders.get(i + 1));
                    sortedOrders.set(i + 1, temp);
                }
                if (sortedOrders.get(i).getStatus() == OrderStatus.CANCELLED &&
                        sortedOrders.get(i + 1).getStatus() == OrderStatus.NEW) {
                    temp = sortedOrders.get(i);
                    sortedOrders.set(i, sortedOrders.get(i + 1));
                    sortedOrders.set(i + 1, temp);
                }
                if (sortedOrders.get(i).getStatus() == OrderStatus.CANCELLED &&
                        sortedOrders.get(i + 1).getStatus() == OrderStatus.FULFILLED) {
                    temp = sortedOrders.get(i);
                    sortedOrders.set(i, sortedOrders.get(i + 1));
                    sortedOrders.set(i + 1, temp);
                }
            }
        }
        System.out.println("Orders sorted by Status:" + sortedOrders.toString());
    }

    //List of book requests (sort by number of requests, alphabetically);
    public void printAllRequestSortedByNumber() {
        List<Request> listReq = repository.getRequests();
        Collections.sort(listReq, Comparator.comparing(Request::getId));
        System.out.println("Requests sorted by number (id):" + listReq.toString());
    }

    public void printAllRequestSortedByAlphabetically() {
        List<Request> requestsList = repository.getRequests();
        Map<String, Request> requestTreeMap = new TreeMap<>();
        int i = 0;
        for (Request req : requestsList) {
            requestTreeMap.put(requestsList.get(i).getBook().getTitle(), requestsList.get(i));
            i++;
        }
        System.out.println("Requests sorted by  title of book Alphabetically:");
        for (Map.Entry<String, Request> entry : requestTreeMap.entrySet()) {
            System.out.println("Title book: " + entry.getKey() + "Request: " + entry.getValue());
        }

    }


// List of completed orders for a period of time (sort by date, by price);

    public List<Order> orderListForPeriod(List<Order> orderList, LocalDate date1, LocalDate date2) {
        int i = 0;
        for (Order ord : orderList) {
            if (ord.getDateExecution().isBefore(date1)
                    && ord.getDateExecution().isAfter(date2))
                orderList.remove(i);
            i++;
        }
        return orderList;
    }

    public void printOrdersSortedByDateForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderListForSort = orderListForPeriod(repository.getOrders(), date1, date2);
        Collections.sort(orderListForSort, Comparator.comparing(Order::getDateOrder));
        System.out.println("Order sorted by Date for period " + date1 + "-" + date2 + ":" + orderListForSort);
    }

    public void printOrdersSortedByPriceForPeriod(LocalDate date1, LocalDate date2) {
        List<Order> orderListForSort = orderListForPeriod(repository.getOrders(), date1, date2);
        Collections.sort(orderListForSort, Comparator.comparing(Order::getTotalPrice));
        System.out.println("Order sorted by Price for period " + date1 + "-" + date2 + ":" + orderListForSort);
    }


    // The amount of money earned over a period of time;
    public void printEarnedMoneyForPeriod(LocalDate date1, LocalDate date2) {
        int earnedMoney = 0;
        List<Order> orderListEarnedMoney = orderListForPeriod(repository.getOrders(), date1, date2);
        for (Order ord : orderListEarnedMoney)
            if (ord.getStatus() == OrderStatus.FULFILLED)
                earnedMoney += ord.getTotalPrice();
        System.out.println("Earned money for period " + date1 + "-" + date2 + ":" + earnedMoney);
    }

    // The number of completed orders over a period of time;
    public void printCountFulfilledOrdersForPeriod(LocalDate date1, LocalDate date2) {
        int countOrders = 0;
        List<Order> orderListEarnedMoney = orderListForPeriod(repository.getOrders(), date1, date2);
        for (Order ord : orderListEarnedMoney)
            if (ord.getStatus() == OrderStatus.FULFILLED)
                countOrders++;
        System.out.println("Count fulfilled orders for period " + date1 + "-" + date2 + ":" + countOrders++);
    }

    //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);
    public void printStaleBooks() {
        List<Book> staleBookList = repository.getStock();
        List<Order> fulfilledListOrders = createListOrdersFulfilled(repository.getOrders());

        Collections.sort(fulfilledListOrders, Comparator.comparing(Order::getDateExecution));
        List<Order> orderList = orderListForPeriod(fulfilledListOrders, LocalDate.now(),
                LocalDate.now().minusMonths(6));

        List<Book> tempList;
        for (Order ord : orderList) {
            if (ord.getStatus() == OrderStatus.FULFILLED) {
                tempList = ord.getListBook();
                for (Book book : tempList)
                    staleBookList.remove(book);
            }
        }
        System.out.println("Books which were not sold for more than 6 months: ");
        System.out.println(staleBookList);
    }

    public List<Order> createListOrdersFulfilled(List<Order> list) {
        List<Order> listFulfilledOrders = new ArrayList<>();
        for (Order ord : list) {
            if (ord.getStatus() == OrderStatus.FULFILLED) {
                listFulfilledOrders.add(ord);
            }
        }
        return listFulfilledOrders;
    }


    //Order details (any customer data + books);
    public void printOrderDetails(int id) {
        for (Order ord : repository.getOrders())
            if (ord.getId() == id) {
                System.out.println("Customer:" + ord.getBuyer());
                System.out.println("Book:" + ord.getListBook());
            }
    }

    //Description of the book.
    public void printDescriptionOfBook(Book book) {
        System.out.println("Description for book with id=" + book.getId() + ":");
        System.out.println(book.getDescription());
    }

}
