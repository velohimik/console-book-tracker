package com.velohimik.repository;

import com.velohimik.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    String saveNewBook(Book book);
    Optional<Book> getBookById(String id);
    List<Book> getBookList();
}
