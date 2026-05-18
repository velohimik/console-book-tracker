package com.velohimik.repository.impl;

import com.velohimik.model.Book;
import com.velohimik.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class FileBookRepository implements BookRepository {

    private static final List<Book> BOOKS = List.of(
            Book.createNewBook("Any Title", "Anyone", "2000"),
            Book.createNewBook("No one knows", "Nemo", "1900"));

    @Override
    public String saveNewBook(Book book) {
        return book.getId();
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Book> getBookList() {
        return BOOKS;
    }
}
