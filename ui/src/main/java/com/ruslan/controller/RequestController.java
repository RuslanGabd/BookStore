package com.ruslan.controller;

import com.ruslan.controller.webExceptions.NoSuchEntityException;
import com.ruslan.dto.OrderDto;
import com.ruslan.dto.RequestDto;
import com.ruslan.services.RequestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<RequestDto>> showAllRequests() {
        log.info("Received GET request /requests/all");
        return ResponseEntity.ok(requestService.listRequestDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> showRequest(@PathVariable int id) {
        log.info("Received GET request /requests/" + id);
        return ResponseEntity.ok(requestService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<RequestDto> saveRequest(@RequestBody RequestDto dto) {
        log.info("Received POST request /requests");
        requestService.saveRequest(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/")
    public ResponseEntity<RequestDto> updateRequest(@RequestBody RequestDto dto) {
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

    @GetMapping("/requests-sorted-by-number")
    public ResponseEntity<List<RequestDto>> showAllRequestsSortedByNumber() {
        log.info("Received GET request /requests/request-sorted-by-number");
        return ResponseEntity.ok(requestService.RequestsDtoSortedByNumber());
    }

    @GetMapping("/requests-sorted-by-alphabetically")
    public ResponseEntity<List<RequestDto>> showAllRequestsSortedByAlphabetically() {
        log.info("Received GET request /requests/request-sorted-by-alphabetically");
        return ResponseEntity.ok(requestService.RequestsDtoSortedByAlphabetically());
    }


    @PostMapping("/createReqeusts")
    public ResponseEntity<RequestDto> addRequest (@RequestParam(value = "bookId") Integer idBook) {
        log.info("Received POST request /requests/add/"+ idBook);
        return ResponseEntity.ok(requestService.createRequestByBookId(idBook));
    }
}
