package example.service.impl;

import example.enums.Error;
import example.model.Book;
import example.repository.BookRepository;
import example.service.Validator;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SaveBookValidator implements Validator {

    private final BookRepository bookRepository;

    public SaveBookValidator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Error> validate(Book book) {
        List<Error> errorList = new ArrayList<>();
        List<Book> bookList = bookRepository.getBookList();
        checkTheBookTitleIsAlreadyExist(book, bookList).ifPresent(errorList::add);
        checkBookYearIsInPast(book).ifPresent(errorList::add);

        return errorList;
    }

    private Optional<Error> checkTheBookTitleIsAlreadyExist(Book validatingBook, List<Book> bookList) {
        for (Book book : bookList) {
            if (book.getTitle().equals(validatingBook.getTitle())) {
                return Optional.of(Error.EXISTING_TITLE);
            }
        }

        return Optional.empty();
    }

    private Optional<Error> checkBookYearIsInPast(Book validatingBook) {
        if (validatingBook.getYear().compareTo(Year.now()) > 0) {
            return Optional.of(Error.INCORRECT_YEAR);
        }

        return Optional.empty();
    }
}