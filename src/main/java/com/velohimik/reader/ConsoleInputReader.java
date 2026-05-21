package com.velohimik.reader;

import com.velohimik.exceptions.BadCommandKeyException;
import com.velohimik.validator.UserInputValidator;

import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner scanner;
    private final UserInputValidator userInputValidator;

    public ConsoleInputReader(Scanner scanner, UserInputValidator userInputValidator) {
        this.scanner = scanner;
        this.userInputValidator = userInputValidator;
    }

    public int readValidCommandKey(int numberOfCommands) {
        int commandKey = Integer.parseInt(scanner.nextLine().trim());
        if (userInputValidator.validateUserInputIsCommandKey(commandKey, numberOfCommands)) {
            return commandKey;
        } else {
            throw new BadCommandKeyException();
        }
    }

    public String readValidBookTitle(String inputPrompt) {
        while(true) {
            System.out.print(inputPrompt);
            String bookTitle = scanner.nextLine().trim();
            if (!bookTitle.isBlank()) {
                return bookTitle;
            }
        }
    }

    public String readValidBookAuthor(String inputPrompt) {
        while(true) {
            System.out.print(inputPrompt);
            String bookAuthor = scanner.nextLine().trim();
            if (!bookAuthor.isBlank()) {
                return bookAuthor;
            }
        }
    }

    public String readValidBookPublishedYear(String inputPrompt) {
        while(true) {
            System.out.print(inputPrompt);
            String bookYear = scanner.nextLine().trim();
            if (userInputValidator.validatePublishedYear(bookYear)) {
                return bookYear;
            }
        }
    }

    public String readValidBookUUID(String inputPrompt) {
        while(true) {
            System.out.print(inputPrompt);
            String bookId = scanner.nextLine().trim();
            if (userInputValidator.validateIdHasUUIDFormat(bookId)) {
                return bookId;
            }
        }
    }
}
