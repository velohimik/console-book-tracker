package com.velohimik.command.impl;

import com.velohimik.command.MenuCommand;
import com.velohimik.service.BookService;
import com.velohimik.validator.UserInputValidator;

import java.util.Scanner;

public class AddNewBookCommand implements MenuCommand {

    private static final String BOOK_TITLE = "\tEnter book title (can't be blank): ";
    private static final String BOOK_AUTHOR = "\tEnter book author (can't be blank): ";
    private static final String BOOK_YEAR = "\tEnter the year book was published (should be 4-digits number): ";
    private static final String ADD_NEW_BOOK = "Add new book;";

    private final BookService bookService;
    private final UserInputValidator userInputValidator;
    private final Scanner scanner;

    public AddNewBookCommand(BookService bookService, UserInputValidator userInputValidator, Scanner scanner) {
        this.bookService = bookService;
        this.userInputValidator = userInputValidator;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        String bookTitle = getTitleFromUserInput();
        String bookAuthor = getAuthorFromUserInput();
        String bookYear = getYearFromUserInput();
        System.out.println(bookService.saveNewBook(bookTitle, bookAuthor, bookYear));
    }

    private String getTitleFromUserInput() {
        String bookTitle;
        do {
            System.out.print(BOOK_TITLE);
            bookTitle = getUserChoice();
        } while (userInputValidator.validateStringIsBlank(bookTitle));
        return bookTitle;
    }

    private String getAuthorFromUserInput() {
        String bookAuthor;
        do {
            System.out.print(BOOK_AUTHOR);
            bookAuthor = getUserChoice();
        } while (userInputValidator.validateStringIsBlank(bookAuthor));
        return bookAuthor;
    }

    private String getYearFromUserInput() {
        String bookYear;
        do {
            System.out.print(BOOK_YEAR);
            bookYear = getUserChoice();
        } while (!userInputValidator.validateYearIsFourDigits(bookYear));
        return bookYear;
    }

    private String getUserChoice() {
        return scanner.nextLine().trim();
    }

    @Override
    public String getDescription() {
        return ADD_NEW_BOOK;
    }
}
