package com.velohimik.service.impl;

import com.velohimik.exceptions.BookIdDoesNotExistsException;
import com.velohimik.exceptions.NoBooksInDatabaseException;
import com.velohimik.model.Book;
import com.velohimik.repository.BookRepository;
import com.velohimik.service.BookService;
import com.velohimik.validator.SaveBookValidator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleBookService implements BookService {

    private final BookRepository bookRepository;
    private final SaveBookValidator saveBookValidator;

    public SimpleBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.saveBookValidator = new SaveBookValidator(bookRepository);
    }

    @Override
    public UUID saveNewBook(String title, String author, String year) {
        saveBookValidator.checkTheBookTitleIsAlreadyExist(title);
        saveBookValidator.checkBookYearIsInPast(year);
        Book newBook = Book.createNewBook(title, author, year);
        return bookRepository.saveNewBook(newBook);
    }

    @Override
    public Book findBookById(String id) {
        Optional<Book> foundBook = bookRepository.getBookById(UUID.fromString(id));
        return foundBook
                .orElseThrow(() -> new BookIdDoesNotExistsException("Book with id %s does not exists in database".formatted(id)));
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.getBookList();
        if (bookList.isEmpty()) {
            throw new NoBooksInDatabaseException("There are no books in your library. Please add one.");
        } else {
            return bookList;
        }
    }
}
