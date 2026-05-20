package com.velohimik.command.impl;

import com.velohimik.command.MenuCommand;
import com.velohimik.service.BookService;
import com.velohimik.validator.UserInputValidator;

import java.util.Scanner;

public class GetBookByIdCommand implements MenuCommand {

    private static final String BOOK_ID = "\tEnter the book id you want to see (should be a number): ";

    private final BookService bookService;
    private final UserInputValidator userInputValidator;
    private final Scanner scanner;

    public GetBookByIdCommand(BookService bookService, UserInputValidator userInputValidator, Scanner scanner) {
        this.bookService = bookService;
        this.userInputValidator = userInputValidator;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        String bookId;
        do {
            System.out.print(BOOK_ID);
            bookId = getUserChoice();
        } while (!userInputValidator.validateIdIsUUID(bookId));
        System.out.println(bookService.findBookById(bookId));
    }

    private String getUserChoice() {
        return scanner.nextLine().trim();
    }

    @Override
    public String getDescription() {
        return "Search the book by its ID;";
    }
}
