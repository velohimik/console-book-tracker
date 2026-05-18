package com.velohimik.service;

public interface BookService {

    String saveNewBook(String title, String author, String year);
    String findBookById(String id);
    String getAllBooks();
}
