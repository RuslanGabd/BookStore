package com.ruslan.dto;

import com.ruslan.entity.book.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class BookDto {

    private final Integer id;
    private final String title;
    private final String author;
    private final Integer price;
    private final String description;
    private final BookStatus status;
    private final LocalDate datePublication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(title, bookDto.title) && Objects.equals(author, bookDto.author) && Objects.equals(price, bookDto.price) && Objects.equals(description, bookDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price, description);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
