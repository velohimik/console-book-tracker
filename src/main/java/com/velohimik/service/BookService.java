package com.velohimik.service;

import com.velohimik.model.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

    UUID saveNewBook(String title, String author, String year);
    Book findBookById(String id);
    List<Book> getAllBooks();
}
