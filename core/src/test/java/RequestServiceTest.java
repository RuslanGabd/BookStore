import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.entity.book.Book;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.request.Request;
import com.ruslan.json.JsonReader;
import com.ruslan.services.RequestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;
    @InjectMocks
    private RequestService requestService;
    private final JsonReader jsonReader = JsonReader.getInstance();
    public String pathBookJSON = "src\\test\\resources\\BooksForTest.json";
    public String pathRequestJSON = "src\\test\\resources\\RequestsForTest.json";
    public String pathOrderJSON = "src\\test\\resources\\OrdersForTest.json";

    List<Book> bookList;
    List<Request> requestList;
    List<Order> orderList;

    @BeforeEach
    void setUp()  {
        bookList = jsonReader.readEntities(Book.class, pathBookJSON);

        requestList = jsonReader.readEntities(Request.class, pathRequestJSON);

        orderList = jsonReader.readEntities(Order.class, pathOrderJSON);
    }
    @Test
    void getRequestSortedByNumber_returnedSortedList() {
        when(requestRepository.findAll()).thenReturn(requestList);
        List<Request> sortedRequestList = requestService.getRequestSortedByNumber();
        Assertions.assertEquals(sortedRequestList.get(0).getId(), 1);
        verify(requestRepository, times(1)).findAll();
    }
    @Test
    void getRequestSortedByAlphabetically_returnedSortedList() {
        when(requestRepository.findAll()).thenReturn(requestList);
        List<Request> sortedRequestList = requestService.getRequestSortedByAlphabetically();
        Assertions.assertEquals(sortedRequestList.get(0).getId(), 2);
        verify(requestRepository, times(1)).findAll();
    }
}
