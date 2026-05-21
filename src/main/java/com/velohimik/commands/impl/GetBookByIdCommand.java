package com.velohimik.commands.impl;

import com.velohimik.commands.MenuCommand;
import com.velohimik.reader.ConsoleInputReader;
import com.velohimik.service.BookService;

public class GetBookByIdCommand implements MenuCommand {

    public static final String INPUT_PROMPT = "\tEnter the book id you want to see (should be UUID format): ";

    private final BookService bookService;
    private final ConsoleInputReader consoleInputReader;

    public GetBookByIdCommand(BookService bookService, ConsoleInputReader consoleInputReader) {
        this.bookService = bookService;
        this.consoleInputReader = consoleInputReader;
    }

    @Override
    public String execute() {
        String bookId = consoleInputReader.readValidBookUUID(INPUT_PROMPT);
        return bookService.findBookById(bookId).toString();
    }

    @Override
    public String getDescription() {
        return "Search the book by its ID;";
    }
}
