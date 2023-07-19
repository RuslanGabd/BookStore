package com.ruslan.services;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.order.OrderStatus;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.services.sinterface.IBookService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final RequestRepository requestRepository;
    private final OrderRepository orderRepository;


    public BookService(BookRepository bookRepository, OrderRepository orderRepository, RequestRepository requestRepository) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public Book createBook(String title, String author, int price, LocalDate datePublication) {
        Book bk = new Book(title, author, price, datePublication);
        bookRepository.saveBook(bk);
        System.out.println("Created book: " + bk.toString());
        return bk;
    }

    @Override
    public void changeStatusBook(int bookId, BookStatus status) {
        bookRepository.updateStatus(bookId, status);
        System.out.println("Book id=" + bookId + " changed status " + status);
    }

    @Override
    public void removeBookFromStock(int bookId) {
        bookRepository.updateStatus(bookId, BookStatus.OUT_OF_STOCK);
        System.out.println("Book id=" + bookId + " status changed OUT_OF_STOCK");
    }

    public void addBookToStockAndCancelRequests(int bookId) {
        bookRepository.updateStatus(bookId, BookStatus.IN_STOCK);
        cancelRequestsByIdBook(bookId);
        System.out.println("Book " + bookId + " add to stock");
    }

    public void cancelRequestsByIdBook(int bookId) {
        for (Request req : requestRepository.getRequestList())
            if (req.getBook().equals(bookRepository.getById(bookId))) {
                requestRepository.removeRequest(req.getId());
                System.out.println("Request id=" + req.getId() + " canceled");
            }
    }



    //Description of the book.
    public void printDescriptionOfBook(int bookId) {
        System.out.println("Description for book with id=" + bookId + ":");
        System.out.println(bookRepository.getById(bookId).getDescription());
    }

    //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);
    public List<Order> orderListForPeriodByExecutionDate(List<Order> orderList, LocalDate date1, LocalDate date2) {
        return orderList.stream()
                .filter(order ->
                        order.getDateExecution().isAfter(date1)
                                && order.getDateExecution().isBefore(date2))
                .collect(Collectors.toList());
    }

    public List<Book> getStaleBooks() {
        List<Book> staleBookList = bookRepository.getBooksList();
        List<Order> fulfilledListOrders =
                orderRepository.getOrdersList()
                        .stream()
                        .filter(order ->
                                order.getStatus() == OrderStatus.FULFILLED)
                        .toList();
        List<Order> orderList = orderListForPeriodByExecutionDate(fulfilledListOrders, LocalDate.now().minusMonths(6),
                LocalDate.now());
        orderList.forEach(order -> staleBookList.removeAll(order.getListBook()));
        return staleBookList;
    }

    public List<Book> getStaleBooksSortedByDate() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        Collections.sort(sortedBooks, Comparator.comparing(Book::getDatePublication));
        return sortedBooks;
    }

    public List<Book> getStaleBooksSortedByPrice() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        Collections.sort(sortedBooks, Comparator.comparing(Book::getPrice));
        return sortedBooks;
    }

    //   List of books (sort alphabetically, by date of publication, by price, by stock availability);
    @Override
    public void printList(String header, List<Book> list) {
        System.out.println(header);
        for (Book bk : list) {
            System.out.println(bk.toString() + "; ");
        }
        System.out.println();
    }


    public List<Book> getBooksSortedByTitleAlphabetically() {
        List<Book> bookList = bookRepository.getBooksList();
        Collections.sort(bookList, (o1, o2) -> CharSequence.compare(o2.getTitle(), o1.getTitle()));
        return bookList;
    }


    public List<Book> getBooksSortedByDatePublication() {
        List<Book> bookList = bookRepository.getBooksList();
        Collections.sort(bookList, Comparator.comparing(Book::getDatePublication));
        return bookList;
    }


    public List<Book> getBooksSortedByPrice() {
        List<Book> bookList = bookRepository.getBooksList();
        Collections.sort(bookList, Comparator.comparingInt(Book::getPrice));
        return bookList;
    }

    public List<Book> getBooksSortedByStatus() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        Book temp;
        for (Book bk : bookRepository.getBooksList()) {
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
        return sortedBooks;
    }

    public List<Book> getBooksSortedByAuthor() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        Collections.sort(sortedBooks, (o1, o2) -> CharSequence.compare(o2.getAuthor(), o1.getAuthor()));
        return sortedBooks;
    }
}
