package com.velohimik.commands.impl;

import com.velohimik.commands.MenuCommand;
import com.velohimik.model.Book;
import com.velohimik.service.BookService;

import java.util.List;

public class GetAllBooksCommand implements MenuCommand {

    private final BookService bookService;

    public GetAllBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute() {
        return convertBookListToString(bookService.getAllBooks());
    }

    @Override
    public String getDescription() {
        return "Look at the list of all books;";
    }

    private static String convertBookListToString(List<Book> bookList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bookList.size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(bookList.get(i));
        }
        return stringBuilder.toString();
    }
}
