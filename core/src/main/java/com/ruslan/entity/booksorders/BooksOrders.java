package java.com.ruslan.entity.booksorders;

import java.com.ruslan.entity.BaseEntity;
import java.com.ruslan.entity.book.Book;
import java.com.ruslan.entity.order.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;


@Table(name = "booksorder", schema = "bookstore")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksOrders extends BaseEntity<Integer> {

    @ManyToOne
    private Book book;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Instant createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BooksOrders that = (BooksOrders) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
