package com.ruslan.data.repository;


import com.ruslan.DI.annotation.PostConstruct;
import com.ruslan.data.book.Book;
import com.ruslan.data.book.BookCounted;
import com.ruslan.data.book.BookStatus;
import com.ruslan.data.repository.rinterface.IBookRepository;
import com.ruslan.jsonHandlers.JsonReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookRepository implements IBookRepository {
    public String pathBookSJSON = "src\\main\\resources\\Books.json";
    private Map<Integer, Book> booksMap = new HashMap<>();
    private JsonReader jsonReader = JsonReader.getInstance();

    @PostConstruct
    public void importBooksFromJson() {
        List<Book> bookList = jsonReader.readEntities(Book.class, pathBookSJSON);
        bookList.forEach(book -> this.addBook(book.getId(), book));
    }


    public void saveBook(Book book) {
        int idBook = BookCounted.generateNewId();
        book.setId(idBook);
        booksMap.put(idBook, book);
    }

    public void addBook(int idBook, Book book) {
        booksMap.put(idBook, book);
    }

    public Book getById(int id) {
        return booksMap.get(id);
    }


    @Override
    public void updateStatus(int id, BookStatus status) {
        booksMap.get(id).setStatus(status);
    }

    @Override
    public void removeById(int idBook) {
        booksMap.remove(idBook);
    }

    @Override
    public List<Book> getBooksList() {
        return new ArrayList<>(booksMap.values());
    }

    public void saveAll(List<Book> bookList) {
        bookList.forEach(this::saveBook);
    }
}
