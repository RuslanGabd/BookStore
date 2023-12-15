package com.ruslan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class RequestDto {

    private final Integer id;
    private final String title;
    private final String author;
    private final Integer price;
    private final String description;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestDto bookDto = (RequestDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(title, bookDto.title)
                && Objects.equals(author, bookDto.author) && Objects.equals(price, bookDto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, price);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
