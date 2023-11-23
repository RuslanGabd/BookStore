package com.ruslan.database.dto;

import com.ruslan.entity.book.BookStatus;

import java.time.LocalDate;

public record BookCreateDto(String title,
                            String author,
                            BookStatus bookStatus,
                            Integer price,
                            String description,
                            LocalDate datePublication) {
}

