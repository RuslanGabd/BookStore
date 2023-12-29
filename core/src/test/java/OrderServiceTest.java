import com.ruslan.database.DAO.OrderRepository;
import com.ruslan.database.DAO.RequestRepository;
import com.ruslan.entity.order.Order;
import com.ruslan.entity.order.OrderStatus;
import com.ruslan.entity.request.Request;
import com.ruslan.json.JsonReader;
import com.ruslan.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private RequestRepository requestRepository;
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;
    private final JsonReader jsonReader = JsonReader.getInstance();
    public String pathRequestSJSON = "src\\test\\resources\\RequestsForTest.json";
    public String pathOrderJSON = "src\\test\\resources\\OrdersForTest.json";


    List<Request> requestList;
    List<Order> orderList;

    @BeforeEach
    void setUp() {
        requestList = jsonReader.readEntities(Request.class, pathRequestSJSON);
        orderList = jsonReader.readEntities(Order.class, pathOrderJSON);
    }

    @Test
    void getOrdersSortedByDateExecution() {
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> sortedListOrders = orderService.getOrdersSortedByDateExecution();
        Assertions.assertEquals(sortedListOrders.get(0).getId(), 1);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrdersSortedByPrice() {
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> sortedListOrders = orderService.getOrdersSortedByPrice();
        Assertions.assertEquals(sortedListOrders.get(0).getTotalPrice(), 150);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrdersSortedByStatus() {
        when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> sortedListOrders = orderService.getOrdersSortedByStatus();
        Assertions.assertEquals(sortedListOrders.get(0).getId(), 1);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void changeOrderStatusToCompletedWithOpenedRequest() {
        Order order = orderList.get(0);
        Request request = requestList.get(1);

        when(orderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(order));
        when(requestRepository.findRequestByBookId(anyInt())).thenReturn(Optional.ofNullable(request));
        assert order != null;
        orderService.changeOrderStatus(order.getId(), OrderStatus.COMPLETED);
        Assertions.assertEquals(order.getStatus(), OrderStatus.NEW);
        verify(orderRepository, times(1)).findById(notNull());
        verify(requestRepository, times(2)).findRequestByBookId(notNull());
    }

    @Test
    void changeOrderStatusToCompletedWithOutOpenedRequest() {
        Order order = orderList.get(1);

        when(orderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(order));
        when(requestRepository.findRequestByBookId(anyInt())).thenReturn(Optional.empty());

        assert order != null;
        orderService.changeOrderStatus(order.getId(), OrderStatus.COMPLETED);
        Assertions.assertEquals(order.getStatus(), OrderStatus.COMPLETED);

        verify(orderRepository, times(1)).findById(notNull());
        verify(requestRepository, times(2)).findRequestByBookId(notNull());
    }

    @Test
    void getCountCompletedOrdersForPeriod() {
        List<Order> completedOrderList = orderList.stream()
                .filter(order -> order.getStatus()
                        .equals(OrderStatus.COMPLETED)).toList();
        when(orderRepository.getCompletedOrdersForPeriod(any(), any())).thenReturn(completedOrderList);
        orderService.getCountCompletedOrdersForPeriod(LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 6, 1));
        verify(orderRepository, times(1)).getCompletedOrdersForPeriod(notNull(), notNull());
    }
}
