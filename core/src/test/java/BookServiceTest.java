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
import com.ruslan.services.OrderService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private RequestRepository requestRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private BookService bookService;
    @InjectMocks
    private OrderService orderService;
    private final JsonReader jsonReader = JsonReader.getInstance();
    public String pathBookSJSON = "src\\test\\resources\\BooksForTest.json";
    public String pathRequestSJSON = "src\\test\\resources\\RequestsForTest.json";
    public String pathOrderJSON = "src\\test\\resources\\OrdersForTest.json";

    List<Book> bookList;
    List<Request> requestList;
    List<Order> orderList;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        bookList = jsonReader.readEntities(Book.class, pathBookSJSON);

        requestList = jsonReader.readEntities(Request.class, pathRequestSJSON);

        orderList = jsonReader.readEntities(Order.class, pathOrderJSON);

        initService();
    }


    @Test
    void addBookToStockAndCancelRequests() {
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
    void cancelRequestsByIdBook() {
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
    void getDescriptionOfBook() {
        Book book = bookList.get(0);
        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        String description = bookService.getDescriptionOfBook(anyInt());
        Assertions.assertEquals(description, "The best book 2022 year");
        verify(bookRepository, times(1)).findById(notNull());
    }

    @Test
    void getStaleBooks() {
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
    void getBooksSortedByTitleAlphabetically() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByTitleAlphabetically();
        Assertions.assertEquals(sortedListBooks.get(2).getId(), 4);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBooksSortedByDatePublication() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByDatePublication();
        Assertions.assertEquals(sortedListBooks.get(1).getId(), 6);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBooksSortedByPrice() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByPrice();
        Assertions.assertEquals(sortedListBooks.get(1).getId(), 2);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBooksSortedByAuthor() {
        when(bookRepository.findAll()).thenReturn(bookList);
        List<Book> sortedListBooks = bookService.getBooksSortedByAuthor();
        Assertions.assertEquals(sortedListBooks.get(1).getId(), 5);
        verify(bookRepository, times(1)).findAll();
    }


    private void initService() throws NoSuchFieldException, IllegalAccessException {
        Class bookServiceClass = this.bookService.getClass();

        Field isAutoRequestClosed = bookServiceClass.getDeclaredField("isAutoRequestClosed");
        isAutoRequestClosed.setAccessible(true);
        isAutoRequestClosed.set(this.bookService, true);

        Field numberOfMonths = bookServiceClass.getDeclaredField("numberOfMonths");
        numberOfMonths.setAccessible(true);
        numberOfMonths.set(this.bookService, 5);
    }



    /*@Test
    void discardBook_SetBookStatusTo_OUT_OF_STOCK() {
        when(bookRepository.findById(anyInt())).thenReturn(book1);
        when(bookDao.update(book)).thenReturn(book);
        bookService.discardBook(1);

        assertThat(book.getBookStatus(), is(equalTo(BookStatus.OUT_OF_STOCK)));
    }

    @Test
    void showDescription_GetBookDescription() {
        when(bookDao.getDescription(anyInt()))).thenReturn(book.getDescription());
        String descr = bookService.showDescription(1L);

        assertThat(descr, is(equalTo(book.getDescription())));
    }

    @Test
    void unsoldBooks_GetListUnsoldBooks_NotEmptyListAndHasSize3() {
        Book book2 = new Book("Test_book2", "Test_author2", "Test_isbn2",
                450, 35.5, 2019, "Test_description2");
        Book book3 = new Book("Test_book3", "Test_author3", "Test_isbn3",
                550, 45.5, 2020, "Test_description3");
        Set<Book> list1 = new HashSet<>();
        list1.add(book1);
        Set<Book> list2 = new HashSet<>();
        list2.add(book2);
        list2.add(book3);
        when(orderDao.getBooksThatAreNotBought(anyInt())).thenReturn(list1);
        when(bookDao.getBookThatHaveNoOrdersForPeriodOfTime(anyInt())).thenReturn(list2);
        List<Book> books = bookService.unsoldBooks();

        assertThat(books, is(not(empty())));
        assertThat(books, hasSize(3));
    }*/
}
