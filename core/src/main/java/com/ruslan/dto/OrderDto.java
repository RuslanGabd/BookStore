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
    private final List<Integer> idBooks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(buyer, orderDto.buyer) && Objects.equals(address, orderDto.address) && status == orderDto.status && Objects.equals(totalPrice, orderDto.totalPrice) && Objects.equals(dateCreated, orderDto.dateCreated) && Objects.equals(dateExecution, orderDto.dateExecution) && Objects.equals(idBooks, orderDto.idBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyer, address, status, totalPrice, dateCreated, dateExecution, idBooks);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", buyer='" + buyer + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", dateCreated=" + dateCreated +
                ", dateExecution=" + dateExecution +
                ", idBooks=" + idBooks +
                '}';
    }
}
