package example.repository.impl;

import example.model.Book;
import example.repository.BookRepository;

import java.util.List;
import java.util.Optional;

public class ReadOnlyBookRepository implements BookRepository {

    private static final List<Book> BOOKS = List.of(
            Book.createNewBook("Any Title", "Anyone", "2000"),
            Book.createNewBook("No one knows", "Nemo", "1900"));

    @Override
    public String saveNewBook(Book book) {
        return "This is a read-only implementation of BookRepository interface. You can't add new books";
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return Optional.ofNullable(BOOKS.getFirst());
    }

    @Override
    public List<Book> getBookList() {
        return BOOKS;
    }
}
