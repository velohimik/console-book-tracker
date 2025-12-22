package example.repository.impl;

import example.model.Book;
import example.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {

    private final static List<Book> BOOK_LIST = new ArrayList<>();

    @Override
    public int saveNewBook(Book book) {
        BOOK_LIST.add(book);
        return book.getId();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        for (Book book : BOOK_LIST) {
            if (book.getId() == id) {
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
