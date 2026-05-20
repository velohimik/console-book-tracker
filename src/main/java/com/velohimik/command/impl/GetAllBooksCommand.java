package com.velohimik.command.impl;

import com.velohimik.command.MenuCommand;
import com.velohimik.service.BookService;

public class GetAllBooksCommand implements MenuCommand {

    private final BookService bookService;

    public GetAllBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void execute() {
        System.out.println(bookService.getAllBooks());
    }

    @Override
    public String getDescription() {
        return "Look at the list of all books;";
    }
}
