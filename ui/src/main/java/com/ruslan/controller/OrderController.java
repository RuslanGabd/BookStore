package com.ruslan.controller;

import com.ruslan.dto.OrderDto;
import com.ruslan.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController( OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value ="orders")
    public Collection<OrderDto> getOrders(Model model) {

        model.addAttribute("orders", orderService.listOrderDto() );

        return orderService.listOrderDto();
    }
}
