package com.velohimik.repository;

import com.velohimik.model.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    UUID saveNewBook(Book book);
    Optional<Book> getBookById(UUID id);
    List<Book> getBookList();
}
