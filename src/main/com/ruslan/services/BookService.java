package com.ruslan.services;

import com.ruslan.DI.annotation.Inject;
import com.ruslan.config.ConfigProperties;
import com.ruslan.config.ConfigPropertiesOld;
import com.ruslan.config.Configuration;
import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.order.Order;
import com.ruslan.data.repository.rinterface.IBookRepository;
import com.ruslan.data.repository.rinterface.IOrderRepository;
import com.ruslan.data.repository.rinterface.IRequestRepository;
import com.ruslan.jsonHandlers.JsonReader;
import com.ruslan.jsonHandlers.JsonWriter;
import com.ruslan.services.sinterface.IBookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Configuration
public class BookService implements IBookService {

    public static String pathBookSJSON = "src\\main\\resources\\Books.json";

    private static final Logger logger = LogManager.getLogger();
    private static final String fileName = "Books.csv";
    @Inject
    private IBookRepository bookRepository;
    @Inject
    private IRequestRepository requestRepository;
    @Inject
    private IOrderRepository orderRepository;

    @ConfigProperties(propertyName = "auto-request-closed-when-book-add-to-stock", type = Boolean.class)
    private Boolean isAutoRequestClosed;
    @ConfigProperties(propertyName = "number-of-months-to-mark-book-stale", type = Integer.class)
    private Integer numberOfMonths;

    private JsonReader jsonReader = JsonReader.getInstance();
    private JsonWriter jsonWriter = JsonWriter.getInstance();
    private ConfigPropertiesOld configProperties = ConfigPropertiesOld.getINSTANCE();

    @Override
    public Book createBook(String title, String author, int price, LocalDate datePublication) {
        Book bk = new Book(title, author, price, datePublication);
        bookRepository.saveBook(bk);
        System.out.println("Created book: " + bk);
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
        if (this.isAutoRequestClosed) {
            cancelRequestsByIdBook(bookId);
        }
        System.out.println("Book " + bookId + " add to stock");
    }

    public void cancelRequestsByIdBook(int bookId) {
        requestRepository.getRequestList().stream()
                .filter(request ->
                        request.getBook()
                                .equals(bookRepository.getById(bookId)))
                .forEach(request -> {
                    requestRepository
                            .removeRequest(request.getId());
                    System.out.println("Request id=" + request.getId() + " canceled");
                });
    }


    //Description of the book.
    public String getDescriptionOfBook(int bookId) {
        return "Description for book with id=" + bookId + ":\n" +
                bookRepository.getById(bookId).getDescription();
    }

    //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);

    public List<Book> getStaleBooks() {
        List<Book> staleBookList = bookRepository.getBooksList();
        List<Order> orderList = null;

        orderList = orderRepository.getCompletedOrdersForPeriod(
                LocalDate.now().minusMonths(numberOfMonths),
                LocalDate.now());

        orderList.forEach(order -> staleBookList.removeAll(order.getListBook()));
        return staleBookList;
    }

    public List<Book> getStaleBooksSortedByDate() {
        List<Book> sortedBooks = getStaleBooks();
        sortedBooks.sort(Comparator.comparing(Book::getDatePublication));
        return sortedBooks;
    }

    public List<Book> getStaleBooksSortedByPrice() {
        List<Book> sortedBooks = getStaleBooks();
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

    public void writeBookToFile(int id) {
        File bookFile = new File(fileName);
        FileOutputStream bookCSV;
        ObjectOutputStream oos;
        List<Book> bookList;

        try {
            bookFile.createNewFile();
            bookList = getBookListFromFile();
            bookList.add(bookRepository.getById(id));
            bookCSV = new FileOutputStream(bookFile);
            oos = new ObjectOutputStream(bookCSV);
            oos.writeObject(bookList);
            oos.close();
        } catch (NullPointerException e) {
            System.out.println("Could not get data from file.");
            logger.error("Could not get data to file", e);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please repeat operation");
            logger.error("File not found", e);
        } catch (IOException e) {
            System.out.println("Could not write data to file");
            logger.error("Could not write data from file", e);
        }
    }

    public List<Book> getBookListFromFile() {
        FileInputStream fis;
        ObjectInputStream ois;
        List<Book> bookList;

        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            bookList = (List<Book>) ois.readObject();
            ois.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class 'Book' not found!");
            logger.error("Class 'Book' not found!", e);
            return null;
        } catch (EOFException e) {
            System.out.println("File Books.csv was empty");
            return bookList = new ArrayList<>();
        } catch (StreamCorruptedException e) {
            System.out.println("Uncorrected data in file. Data was erased");
            return bookList = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Could not read data from file");
            logger.error("Could not read data from file", e);
            return null;
        }
        return bookList;
    }

    public Book getBookFromFile(int id) {
        return getBookListFromFile()
                .stream()
                .filter(book ->
                        book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void exportBooksToJson() {
        jsonWriter.writeEntities(bookRepository.getBooksList(), pathBookSJSON);
    }
}