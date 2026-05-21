package com.velohimik.validator;

import com.velohimik.exceptions.ExistingBookTitleException;
import com.velohimik.exceptions.InvalidPublishedYearException;
import com.velohimik.repository.BookRepository;

import java.time.Year;

public class SaveBookValidator {

    private final BookRepository bookRepository;

    public SaveBookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void checkTheBookTitleIsAlreadyExist(String bookTitle) {
        boolean titleIsAlreadyExists = bookRepository.getBookList()
                .stream()
                .anyMatch(book -> book.getTitle().equalsIgnoreCase(bookTitle));
        if (titleIsAlreadyExists) {
            throw new ExistingBookTitleException("The book with title %s already exists in the library".formatted(bookTitle));
        }
    }

    public void checkBookYearIsInPast(String bookYear) {
        boolean yearIsFuture = Year.parse(bookYear).compareTo(Year.now()) > 0;
        if (yearIsFuture) {
            throw new InvalidPublishedYearException("Entered published year %s is a future year".formatted(bookYear));
        }
    }
}