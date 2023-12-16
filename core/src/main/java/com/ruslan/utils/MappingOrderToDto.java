package com.ruslan.utils;

import com.ruslan.dto.BookDto;
import com.ruslan.dto.OrderDto;
import com.ruslan.entity.order.Order;
import org.springframework.stereotype.Service;

@Service
public class MappingOrderToDto {

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getBuyer(), order.getAddress(), order.getStatus(),
                order.getTotalPrice(), order.getDateExecution(), order.getDateCreated());
    }

    public Order mapToOrder(OrderDto dto) {
        return new Order( dto.getBuyer(), dto.getAddress(), dto.getStatus(), dto.getTotalPrice(),
                dto.getDateExecution(), dto.getDateCreated());
    }
}