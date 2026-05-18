package com.velohimik.repository.impl;

import com.velohimik.model.Book;
import com.velohimik.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {

    private final static List<Book> BOOK_LIST = new ArrayList<>();

    @Override
    public String saveNewBook(Book book) {
        BOOK_LIST.add(book);
        return book.getId();
    }

    @Override
    public Optional<Book> getBookById(String id) {
        for (Book book : BOOK_LIST) {
            if (book.getId().equals(id)) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Book> getBookList() {
        return BOOK_LIST;
    }
}
