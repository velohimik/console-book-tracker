package com.velohimik.ui;

import com.velohimik.command.MenuCommand;
import com.velohimik.command.impl.AddNewBookCommand;
import com.velohimik.command.impl.ExitCommand;
import com.velohimik.command.impl.GetAllBooksCommand;
import com.velohimik.command.impl.GetBookByIdCommand;
import com.velohimik.service.BookService;
import com.velohimik.validator.UserInputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLIManager {

    private static final String WELCOME_MESSAGE = "\nWelcome to Your Library!!! Oops, let's be more quite. Please enter the relevant digit:\n";
    private static final String BLOCK_SEPARATE = "============================";
    private static final String USER_INPUT_MESSAGE = "\tYour choice: ";
    private static final String FOLLOW_INSTRUCTIONS_MESSAGE = "!!! Wrong input. Please make the enter according to the instruction:\n";

    private final Map<Integer, MenuCommand> commands = new HashMap<>();
    private final Scanner scanner;
    private final UserInputValidator userInputValidator;

    private CLIManager(BookService bookService, UserInputValidator userInputValidator, Scanner scanner) {
        commands.put(1, new AddNewBookCommand(bookService, userInputValidator, scanner));
        commands.put(2, new GetAllBooksCommand(bookService));
        commands.put(3, new GetBookByIdCommand(bookService, userInputValidator, scanner));
        commands.put(0, new ExitCommand());
        this.scanner = scanner;
        this.userInputValidator = userInputValidator;
    }

    public static void run(BookService bookService, UserInputValidator userInputValidator, Scanner scanner) {
        CLIManager cliManager = new CLIManager(bookService, userInputValidator, scanner);
        cliManager.makeCLI();
    }

    private void makeCLI() {
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            printMenu();
            System.out.print(USER_INPUT_MESSAGE);
            int userChoice = getUserChoice();
            if (userInputValidator.validateUserInputIsCommandKey(userChoice, commands.size())) {
                System.out.println(BLOCK_SEPARATE);
                commands.get(userChoice).execute();
            } else {
                System.out.println(FOLLOW_INSTRUCTIONS_MESSAGE);
            }
        }
    }

    private void printMenu() {
        commands.forEach((key, value) -> System.out.println(key + " - " + value.getDescription()));
    }

    private int getUserChoice() {
        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            scanner.nextLine();
            return i;
        }
        return Integer.MAX_VALUE;
    }
}
