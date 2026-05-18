package com.velohimik;

import com.velohimik.ui.CLIManager;
import com.velohimik.repository.BookRepository;
import com.velohimik.repository.impl.InMemoryBookRepository;
import com.velohimik.service.BookService;
import com.velohimik.service.impl.SimpleBookService;
import com.velohimik.validator.UserInputValidator;

import java.util.Scanner;

public class ConsoleBookTracker {

    public static void main(String[] args) {
        BookRepository bookRepository = new InMemoryBookRepository();
        BookService bookService = new SimpleBookService(bookRepository);
        UserInputValidator userInputValidator = new UserInputValidator();
        Scanner scanner = new Scanner(System.in);
        CLIManager.run(bookService, userInputValidator, scanner);
    }
}
