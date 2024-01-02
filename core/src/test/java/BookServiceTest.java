import com.ruslan.database.DAO.BookRepository;
import com.ruslan.database.DAO.OrderRepository;
import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.book.BookStatus;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.order.OrderStatus;
import com.ruslan.entity.request.Request;
import com.ruslan.json.JsonReader;
import com.ruslan.services.BookService;
import jakarta.annotation.PostConstruct;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@TestPropertySource(locations = "classpath:test_application.yml")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BookServiceTest.TestContextConfiguration.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private RequestRepository requestRepository;

    @MockBean
    private OrderRepository orderRepository;

    private final JsonReader jsonReader = JsonReader.getInstance();
    public String pathBookSJSON = "src\\test\\resources\\BooksForTest.json";
    public String pathRequestSJSON = "src\\test\\resources\\RequestsForTest.json";
    public String pathOrderJSON = "src\\test\\resources\\OrdersForTest.json";

    List<Book> bookList;
    List<Request> requestList;
    List<Order> orderList;

    @PostConstruct
    void setUp() {
        bookList = jsonReader.readEntities(Book.class, pathBookSJSON);

        requestList = jsonReader.readEntities(Request.class, pathRequestSJSON);

        orderList = jsonReader.readEntities(Order.class, pathOrderJSON);
    }

    @Test
    public void addBookToStockAndCancelRequests_positiveWithoutExceptions() {
        Book book = bookList.get(0);
        Request request = requestList.get(1);
        book.setStatus(BookStatus.OUT_OF_STOCK);

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        when(requestRepository.findAll()).thenReturn(Collections.singletonList(request));
        bookService.addBookToStockAndCancelRequests(book.getId());

        assertThat(book.getStatus()).isEqualTo(BookStatus.IN_STOCK);
        verify(bookRepository, times(2)).findById(notNull());
        verify(requestRepository, times(1)).findAll();
        verify(requestRepository, times(1)).delete(notNull());
    }

    @Test
    public void cancelRequestsByIdBook_deleteRequestPositive() {
        Book book = bookList.get(0);

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        when(requestRepository.findAll()).thenReturn(requestList);

        bookService.cancelRequestsByIdBook(book.getId());
        assertThat(book.getStatus()).isEqualTo(BookStatus.IN_STOCK);
        verify(bookRepository, times(requestList.size())).findById(notNull());
        verify(requestRepository, times(1)).findAll();
        verify(requestRepository, times(1)).delete(notNull());
    }

    @Test
    public void getDescriptionOfBook_positive() {
        Book book = bookList.get(0);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        String description = bookService.getDescriptionOfBook(anyInt());
        Assertions.assertEquals(description, "The best book 2022 year");
        verify(bookRepository, times(1)).findById(notNull());
    }

    @Test
    public void getStaleBooks_returnedList() {
        List<Order> completedOrderList = orderList.stream()
                .filter(order -> order.getStatus().equals(OrderStatus.COMPLETED))
                .toList();
        List<Book> staleBooks;
        when(bookRepository.findAll()).thenReturn(bookList);
        when(orderRepository.getCompletedOrdersForPeriod(any(), any())).thenReturn(completedOrderList);

        staleBooks = bookService.getStaleBooks();
        MatcherAssert.assertThat(staleBooks, hasSize(5));
        verify(bookRepository, times(1)).findAll();
        verify(orderRepository, times(1)).getCompletedOrdersForPeriod(notNull(), notNull());
    }

    @Test
    public void getBooksSortedByTitleAlphabetically_returnedSortedList() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByTitleAlphabetically();
        Assertions.assertEquals(sortedListBooks.get(2).getId(), 4);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void getBooksSortedByDatePublication_returnedSortedList() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByDatePublication();
        Assertions.assertEquals(sortedListBooks.get(1).getId(), 6);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void getBooksSortedByPrice_returnedSortedList() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByPrice();
        Assertions.assertEquals(sortedListBooks.get(1).getId(), 2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void getBooksSortedByAuthor_returnedSortedList() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByAuthor();
        Assertions.assertEquals(sortedListBooks.get(1).getId(), 5);
        verify(bookRepository, times(1)).findAll();
    }

    @Configuration
    @ComponentScan(basePackages = "com.ruslan")
    static class TestContextConfiguration {
    }
}
