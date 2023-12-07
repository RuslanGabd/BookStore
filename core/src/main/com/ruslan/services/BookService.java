package com.ruslan.services;

import com.ruslan.DI.config.ConfigPropertiesOld;
import com.ruslan.DI.config.Configuration;
import com.ruslan.database.DAO.BookRepository;
import com.ruslan.database.DAO.OrderRepository;
import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.book.BookStatus;
import com.ruslan.entity.order.Order;
import com.ruslan.json.JsonReader;
import com.ruslan.json.JsonWriter;
import com.ruslan.services.sinterface.IBookService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@Service
@Component
public class BookService implements IBookService {

    public String pathBookSJSON = "src\\main\\resources\\Books.json";

    private final Logger logger = LogManager.getLogger();
    private final String fileName = "Books.csv";

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RequestRepository requestRepository;
    @Value("${auto-request-closed-when-book-add-to-stock:true}")
    private Boolean isAutoRequestClosed;
    @Value("${number-of-months-to-mark-book-stale:5}")
    private Integer numberOfMonths;

    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();
    private final ConfigPropertiesOld configProperties = ConfigPropertiesOld.getINSTANCE();

    @Override
    public Book createBook(String title, String author, int price, LocalDate datePublication) {
        Book bk = bookRepository.save(new Book(title, author, price, datePublication, BookStatus.IN_STOCK, null));
        System.out.println("Created book: " + bk);
        return bk;
    }

    @Override
    public void changeStatusBook(int bookId, BookStatus status) {
        bookRepository.findById(bookId).ifPresent(book -> {
            book.setStatus(status);
            bookRepository.update(book);
        });
    }

    @Override
    public void removeBookFromStock(int bookId) {
        bookRepository.delete(bookId);
    }

    public void addBookToStockAndCancelRequests(int bookId) {
        bookRepository.findById(bookId).ifPresent(book -> book.setStatus(BookStatus.IN_STOCK));
        if (this.isAutoRequestClosed) {
            cancelRequestsByIdBook(bookId);
        }
        System.out.println("Book " + bookId + " add to stock");
    }

    public void cancelRequestsByIdBook(int bookId) {
        requestRepository.findAll().stream()
                .filter(request ->
                        request.getBook()
                                .equals(bookRepository.findById(bookId).orElseGet(null)))
                .forEach(request -> {
                    requestRepository.delete(request.getId());
                    System.out.println("Request id=" + request.getId() + " canceled");
                });
    }


    //Description of the book.

    public String getDescriptionOfBook(int bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.map(Book::getDescription).orElse(null);
    }

    //List of "stale" books which were not sold for more than 6 months. (sort by date of receipt, by price);

    public List<Book> getCompletedOrdersForPeriod() {
        List<Book> staleBookList = bookRepository.findAll();
        List<Order> orderList;

        orderList = orderRepository.getCompletedOrdersForPeriod(
                LocalDate.now().minusMonths(numberOfMonths),
                LocalDate.now());

        orderList.forEach(order -> staleBookList.removeAll(order.getListBook()));
        return staleBookList;
    }

    public List<Book> getStaleBooks() {
        List<Book> staleBookList = bookRepository.findAll();
        List<Order> orderList;

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
        List<Book> bookList = bookRepository.findAll();
        bookList.sort((o1, o2) -> CharSequence.compare(o2.getTitle(), o1.getTitle()));
        return bookList;
    }


    public List<Book> getBooksSortedByDatePublication() {
        List<Book> bookList = bookRepository.findAll();
        bookList.sort(Comparator.comparing(Book::getDatePublication));
        return bookList;
    }


    public List<Book> getBooksSortedByPrice() {
        List<Book> bookList = bookRepository.findAll();
        bookList.sort(Comparator.comparingInt(Book::getPrice));
        return bookList;
    }

    public List<Book> getBooksSortedByStatus() {
        List<Book> bookList = bookRepository.findAll();
        bookList.sort(Comparator.comparing(Book::getStatus));
        return bookList;
    }

    public List<Book> getBooksSortedByAuthor() {
        List<Book> sortedBooks = bookRepository.findAll();
        sortedBooks.sort((o1, o2) -> CharSequence.compare(o2.getAuthor(), o1.getAuthor()));
        return sortedBooks;
    }

    public void writeBookToFile(int id) {
        File bookFile = new File(fileName);
        FileOutputStream bookCSV;
        ObjectOutputStream oos;
        List<Book> bookList;

        try {
            Optional<Book> book = bookRepository.findById(id);
            bookFile.createNewFile();
            bookList = getBookListFromFile();
            bookList.add(book.orElse(null));
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


    public void importBooksFromJsonToDataBase() {
        List<Book> bookList = jsonReader.readEntities(Book.class, pathBookSJSON);
        bookList.forEach(book -> bookRepository.save(book));
    }

    public void exportBooksToJson() {
        jsonWriter.writeEntities(bookRepository.findAll(), pathBookSJSON);
    }
}