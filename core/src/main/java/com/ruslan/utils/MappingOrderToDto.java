package com.ruslan.utils;

import com.ruslan.database.DAO.BookRepository;
import com.ruslan.dto.OrderDto;
import com.ruslan.entity.BaseEntity;
import com.ruslan.entity.order.Order;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class MappingOrderToDto {
    private final BookRepository bookRepository;

    public MappingOrderToDto(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(),
                order.getBuyer(),
                order.getAddress(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getDateExecution(),
                order.getDateCreated(),
                order.getListBook().stream().map(BaseEntity::getId).collect(toList()));
    }

    public Order mapToOrder(OrderDto dto) {
        return new Order( dto.getBuyer(),
                dto.getAddress(),
                dto.getStatus(),
                dto.getTotalPrice(),
                dto.getDateExecution(),
                dto.getDateCreated(),
                dto.getIdBooks().stream().map(id -> bookRepository.findById(id).orElse(null)).collect(toList()));
    }
}