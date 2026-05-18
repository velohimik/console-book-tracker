package com.velohimik.service.impl;

import com.velohimik.enums.Error;
import com.velohimik.model.Book;
import com.velohimik.repository.BookRepository;
import com.velohimik.service.BookService;
import com.velohimik.validator.SaveBookValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleBookService implements BookService {

    private static final String SUCCESSFUL_SAVING_MESSAGE = "The book is saved successfully with id = \"%s\".\n";
    private static final String EMPTY_BOOK_LIST_MESSAGE = "There are no books in your library. Please add one.\n";
    private final BookRepository bookRepository;
    private final SaveBookValidator saveBookValidator;

    public SimpleBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.saveBookValidator = new SaveBookValidator(bookRepository);
    }

    @Override
    public String saveNewBook(String title, String author, String year) {
        List<Error> errors = new ArrayList<>();
        saveBookValidator.checkTheBookTitleIsAlreadyExist(title).ifPresent(errors::add);
        saveBookValidator.checkBookYearIsInPast(year).ifPresent(errors::add);
        if (errors.isEmpty()) {
            Book newBook = Book.createNewBook(title, author, year);
            UUID savedBookId = bookRepository.saveNewBook(newBook);
            return String.format(SUCCESSFUL_SAVING_MESSAGE, savedBookId);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Error error : errors) {
                stringBuilder.append(error.getErrorDescription());
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }

    @Override
    public String findBookById(String id) {
        Optional<Book> foundBook = bookRepository.getBookById(UUID.fromString(id));
        if (foundBook.isPresent()) {
            return foundBook.get().toString();
        }

        return Error.INCORRECT_ID.getErrorDescription();
    }

    @Override
    public String getAllBooks() {
        List<Book> bookList = bookRepository.getBookList();
        if (bookList.isEmpty()) {
            return EMPTY_BOOK_LIST_MESSAGE;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < bookList.size(); i++) {
                stringBuilder.append(i + 1);
                stringBuilder.append(". ");
                stringBuilder.append(bookList.get(i));
                stringBuilder.append("\n");
            }

            return stringBuilder.toString();
        }
    }
}
