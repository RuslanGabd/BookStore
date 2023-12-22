package com.ruslan.dto;

import com.ruslan.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class RequestDto {

    private final Integer id;
    private final LocalDate dateCreated;
    private final Integer idBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDto that = (RequestDto) o;
        return Objects.equals(id, that.id) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(idBook, that.idBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, idBook);
    }

    @Override
    public String toString() {
        return "RequestDto{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", book=" + idBook +
                '}';
    }
}
