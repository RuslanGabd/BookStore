package com.ruslan.dto;

import com.ruslan.entity.book.Book;
import com.ruslan.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class OrderDto {

    private final Integer id;
    private final String buyer;
    private final String address;
    private final OrderStatus status;
    private final Integer totalPrice;
    private final LocalDate dateCreated;
    private final LocalDate dateExecution;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(buyer, orderDto.buyer) && Objects.equals(totalPrice, orderDto.totalPrice) && Objects.equals(status, orderDto.status) && Objects.equals(dateCreated, orderDto.dateCreated) && Objects.equals(dateExecution, orderDto.dateExecution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyer, totalPrice, status, dateCreated, dateExecution);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", buyer='" + buyer + '\'' +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateExecution=" + dateExecution +
                '}';
    }
}
