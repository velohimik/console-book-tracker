package example.service;

import example.model.Book;

public interface BookService {

    String processNewBook(Book book);
    String findBookById(int id);
    String getAllBooks();
}
