package com.velohimik.validator;

import com.velohimik.enums.Error;
import com.velohimik.model.Book;
import com.velohimik.repository.BookRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class SaveBookValidator {

    private final BookRepository bookRepository;

    public SaveBookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Error> checkTheBookTitleIsAlreadyExist(String bookTitle) {
        List<Book> bookList = bookRepository.getBookList();
        for (Book book : bookList) {
            if (book.getTitle().equals(bookTitle)) {
                return Optional.of(Error.EXISTING_TITLE);
            }
        }

        return Optional.empty();
    }

    public Optional<Error> checkBookYearIsInPast(String bookYear) {
        if (Year.parse(bookYear).compareTo(Year.now()) > 0) {
            return Optional.of(Error.INCORRECT_YEAR);
        }

        return Optional.empty();
    }
}