package com.ruslan.controller;

import com.ruslan.controller.webExceptions.NoSuchEntityException;
import com.ruslan.dto.OrderDto;
import com.ruslan.dto.RequestDto;
import com.ruslan.entity.order.OrderStatus;
import com.ruslan.services.RequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RequestDto>> showAllOrders() {
        log.info("Received GET request /requests/all");
        return ResponseEntity.ok(requestService.listRequestDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> showOrder(@PathVariable int id) {
        log.info("Received GET request /requests/" + id);
        return ResponseEntity.ok(requestService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<RequestDto> saveOrder(@RequestBody RequestDto dto) {
        log.info("Received POST request /requests");
        requestService.saveRequest(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/")
    public ResponseEntity<RequestDto> updateOrder(@RequestBody RequestDto dto) {
        log.info("Received PUT request /orders");
        requestService.saveRequest(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public String deleteRequest(@PathVariable int id) {
        log.info("Received DELETE request" + id);
        if (requestService.findById(id) == null) {
            throw new NoSuchEntityException("There is no request with id=" + id + " in database");
        } else {
            requestService.removeRequest(id);
            return "Request with id=" + id + " was deleted";
        }
    }

//    @PatchMapping("/change-status/{status}/{id}")
//    public ResponseEntity<OrderDto> changeOrderStatus(@PathVariable String status,
//                                                      @PathVariable int id) {
//        log.info("Received PATCH request /orders/change-status/" + status + "/" + id);
//        return ResponseEntity.ok(orderService.changeOrderDtoStatus(id, OrderStatus.valueOf(status)));
//    }
//
//    @GetMapping("/count-completed-orders-for-period/{from}/{till}")
//    public ResponseEntity<Integer> getCountCompletedOrdersForPeriod(@PathVariable String from,
//                                                                         @PathVariable String till)  {
//        log.info("Received GET request /orders/count-completed-orders-for-period/");
//        LocalDate date1 = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDate date2 = LocalDate.parse(till, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        return ResponseEntity.ok(orderService.getCountCompletedOrdersForPeriod(date1, date2));
//    }
//
//    @GetMapping("/completed-by-period/{from}/{till}")
//    public ResponseEntity<List<OrderDto>> ordersDoneByPeriodOfTime(@PathVariable String from,
//                                                                @PathVariable String till) {
//        log.info("Received GET request /orders/done-by-period/" + from + "/" + till);
//        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDate tillDate = LocalDate.parse(till, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        return ResponseEntity.ok(orderService.ordersCompletedByPeriodOfTime(fromDate, tillDate));
//    }
//
//    @PostMapping("/createOrder")
//    public ResponseEntity<OrderDto> addOrder( @RequestParam(value = "booksId") List<Integer> booksId) {
//        log.info("Received POST request /orders/add/"+ booksId);
//        return ResponseEntity.ok(orderService.createOrderByListBookId(booksId));
//       }
}
