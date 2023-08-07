package com.ruslan.services;

import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.repository.BookRepository;
import com.ruslan.data.repository.OrderRepository;
import com.ruslan.data.repository.RequestRepository;
import com.ruslan.data.request.Request;
import com.ruslan.services.sinterface.IBookService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


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
    public String getDescriptionOfBook(int bookId) {
        return "Description for book with id=" + bookId + ":\n" +
                bookRepository.getById(bookId).getDescription();
    }

    //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);

    public List<Book> getStaleBooks() {
        List<Book> staleBookList = bookRepository.getBooksList();
        List<Order> orderList = orderRepository.getCompletedOrdersForPeriod(
                LocalDate.now().minusMonths(6),
                LocalDate.now());
        orderList.forEach(order -> staleBookList.removeAll(order.getListBook()));
        return staleBookList;
    }

    public List<Book> getStaleBooksSortedByDate() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        sortedBooks.sort(Comparator.comparing(Book::getDatePublication));
        return sortedBooks;
    }

    public List<Book> getStaleBooksSortedByPrice() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        sortedBooks.sort(Comparator.comparing(Book::getPrice));
        return sortedBooks;
    }

    //   List of books (sort alphabetically, by date of publication, by price, by stock availability);


    public List<Book> getBooksSortedByTitleAlphabetically() {
        List<Book> bookList = bookRepository.getBooksList();
        bookList.sort((o1, o2) -> CharSequence.compare(o2.getTitle(), o1.getTitle()));
        return bookList;
    }


    public List<Book> getBooksSortedByDatePublication() {
        List<Book> bookList = bookRepository.getBooksList();
        bookList.sort(Comparator.comparing(Book::getDatePublication));
        return bookList;
    }


    public List<Book> getBooksSortedByPrice() {
        List<Book> bookList = bookRepository.getBooksList();
        bookList.sort(Comparator.comparingInt(Book::getPrice));
        return bookList;
    }

    public List<Book> getBooksSortedByStatus() {
        List<Book> bookList = bookRepository.getBooksList();
        bookList.sort(Comparator.comparing(Book::getStatus));
        return bookList;
    }

    public List<Book> getBooksSortedByAuthor() {
        List<Book> sortedBooks = bookRepository.getBooksList();
        sortedBooks.sort((o1, o2) -> CharSequence.compare(o2.getAuthor(), o1.getAuthor()));
        return sortedBooks;
    }
}
