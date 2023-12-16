package com.ruslan.dto;

import com.ruslan.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class RequestDto {

    private final Integer id;
    private final LocalDate dateCreated;
    private final Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, book);
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", book=" + book +
                '}';
    }
}
