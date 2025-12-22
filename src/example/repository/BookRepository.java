package example.repository;

import example.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    int saveNewBook(Book book);
    Optional<Book> getBookById(int id);
    List<Book> getBookList();
}
