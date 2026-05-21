package com.velohimik.commands.impl;

import com.velohimik.commands.MenuCommand;
import com.velohimik.reader.ConsoleInputReader;
import com.velohimik.service.BookService;

public class AddNewBookCommand implements MenuCommand {

    public static final String TITLE_INPUT_PROMPT = "\tEnter book title (can't be blank): ";
    public static final String AUTHOR_INPUT_PROMPT = "\tEnter book author (can't be blank): ";
    public static final String PUBLISHED_YEAR_INPUT_PROMPT = "\tEnter the year book was published (should be 4-digits year from the past): ";

    private final BookService bookService;
    private final ConsoleInputReader consoleInputReader;

    public AddNewBookCommand(BookService bookService, ConsoleInputReader consoleInputReader) {
        this.bookService = bookService;
        this.consoleInputReader = consoleInputReader;
    }

    @Override
    public String execute() {
        String bookTitle = consoleInputReader.readValidBookTitle(TITLE_INPUT_PROMPT);
        String bookAuthor = consoleInputReader.readValidBookAuthor(AUTHOR_INPUT_PROMPT);
        String bookPublishedYear = consoleInputReader.readValidBookPublishedYear(PUBLISHED_YEAR_INPUT_PROMPT);
        String savedBookId = bookService.saveNewBook(bookTitle, bookAuthor, bookPublishedYear).toString();
        return String.format("The book is saved successfully with id = \"%s\".", savedBookId);
    }

    @Override
    public String getDescription() {
        return "Add new book;";
    }
}
