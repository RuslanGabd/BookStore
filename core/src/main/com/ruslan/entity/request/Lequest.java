package com.ruslan.entity.request;

import com.ruslan.entity.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "lequest", schema = "bookstore")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "bookid") // company_id
    private Book book;

}
