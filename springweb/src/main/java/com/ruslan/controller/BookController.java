package com.ruslan.controller;


import com.ruslan.controller.webExceptions.NoSuchEntityException;
import com.ruslan.dto.BookDto;
import com.ruslan.entity.book.Book;
import com.ruslan.services.BookService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> showAllBooks() {
        log.info("Received GET request /books");
        return ResponseEntity.ok(bookService.listBookDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> showBook(@PathVariable Integer id) {
        log.info("Received GET request /books/" + id);
        if (bookService.findById(id) == null) {
            throw new NoSuchEntityException("There is no book with id=" + id + " in database");
        }
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto dto) {
        log.info("Received POST request /books");
        bookService.saveBook(dto);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestParam Integer id, @RequestBody BookDto dto) {
        log.info("Received Patch request /books/" + id);
        bookService.saveBook(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto dto) {
        log.info("Received PUT request /books");
        bookService.saveBook(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteBook(@PathVariable Integer id) {
        log.info("Received DELETE request /books/" + id);
        if (bookService.findById(id) == null) {
            throw new NoSuchEntityException("There is no book with id=" + id + " in database");
        } else {
            bookService.deleteBook(id);
            return  ResponseEntity.ok("Book with id=" + id + " was deleted");
        }
    }

    @PutMapping("/add-to-stock/{id}")
    public ResponseEntity<BookDto> addBookToStock(@PathVariable Integer id) {
        log.info("Received PUT request /books/" + id + "/add-to-stock");
        return ResponseEntity.ok(bookService.addBookToStockAndCancelRequestsForHttp(id));
    }


    @GetMapping("/poor-purchased")
    public ResponseEntity<List<Book>> getStaleBooks() {
        log.info("Received GET request /books/StaleBooks");
        return ResponseEntity.ok(bookService.getStaleBooks());
    }
}
