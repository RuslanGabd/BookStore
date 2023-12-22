package com.ruslan.controller;

import com.ruslan.controller.webExceptions.NoSuchEntityException;
import com.ruslan.dto.GenericResponseDto;
import com.ruslan.dto.OrderDto;
import com.ruslan.entity.order.OrderStatus;
import com.ruslan.services.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> showAllOrders() {
        log.info("Received GET request /orders");
        return ResponseEntity.ok(orderService.listOrderDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> showOrder(@PathVariable int id) {
        log.info("Received GET request /orders/" + id);
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto dto) {
        log.info("Received POST request /orders");
        orderService.saveOrder(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto dto) {
        log.info("Received PUT request /orders");
        orderService.saveOrder(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponseDto> deleteOrder(@PathVariable int id) {
        log.info("Received DELETE request /orders/" + id);
        if (orderService.findById(id) == null) {
            throw new NoSuchEntityException("There is no order with id=" + id + " in database");
        } else {
            orderService.removeOrder(id);
            return ResponseEntity.ok(new GenericResponseDto("Order with id=" + id + " was deleted"));
        }
    }

    @PatchMapping("/change-status/{status}/{id}")
    public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable String status,
                                                      @PathVariable int id) {
        log.info("Received PATCH request /orders/change-status/" + status + "/" + id);
        return ResponseEntity.ok(orderService.changeOrderDtoStatus(id, OrderStatus.valueOf(status)));
    }

    @GetMapping("/count-completed-orders-for-period/{from}/{till}")
    public ResponseEntity<GenericResponseDto> getCountCompletedOrdersForPeriod(@PathVariable String from,
                                                                    @PathVariable String till) {
        log.info("Received GET request /orders/count-completed-orders-for-period/");
        LocalDate date1 = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate date2 = LocalDate.parse(till, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return ResponseEntity.ok(new GenericResponseDto(orderService.getCountCompletedOrdersForPeriod(date1, date2)));
    }

    @GetMapping("/completed-by-period/{from}/{till}")
    public ResponseEntity<List<OrderDto>> ordersDoneByPeriodOfTime(@PathVariable String from,
                                                                   @PathVariable String till) {
        log.info("Received GET request /orders/done-by-period/" + from + "/" + till);
        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate tillDate = LocalDate.parse(till, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return ResponseEntity.ok(orderService.ordersCompletedByPeriodOfTime(fromDate, tillDate));
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDto> addOrder(@RequestParam(value = "buyer") String buyer,
                                             @RequestParam(value = "address") String address,
                                             @RequestParam(value = "booksId") List<Integer> booksId) {
        log.info("Received POST request /orders/add/" + booksId);
        return ResponseEntity.ok(orderService.createOrderByListBookId(buyer, address, booksId));
    }
}
