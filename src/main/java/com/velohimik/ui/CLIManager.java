package com.velohimik.ui;

import com.velohimik.commands.MenuCommand;
import com.velohimik.commands.impl.AddNewBookCommand;
import com.velohimik.commands.impl.ExitCommand;
import com.velohimik.commands.impl.GetAllBooksCommand;
import com.velohimik.commands.impl.GetBookByIdCommand;
import com.velohimik.exceptions.ExitApplicationException;
import com.velohimik.reader.ConsoleInputReader;
import com.velohimik.service.BookService;

import java.util.HashMap;
import java.util.Map;

public class CLIManager {

    private static final String WELCOME_MESSAGE = "\nWelcome to Your Library!!! Oops, let's be more quite. Please enter the relevant digit:\n";
    private static final String DASH_LINE = "\n==============================================\n\n";
    private static final String USER_INPUT_MESSAGE = "\n\tYour choice: ";
    private static final String FOLLOW_INSTRUCTIONS_MESSAGE = "\n!!! Wrong input. Please make the enter according to the instruction:\n";

    private final Map<Integer, MenuCommand> commands = new HashMap<>();
    private final ConsoleInputReader consoleInputReader;

    private CLIManager(BookService bookService, ConsoleInputReader consoleInputReader) {
        commands.put(1, new AddNewBookCommand(bookService, consoleInputReader));
        commands.put(2, new GetAllBooksCommand(bookService));
        commands.put(3, new GetBookByIdCommand(bookService, consoleInputReader));
        commands.put(0, new ExitCommand());
        this.consoleInputReader = consoleInputReader;
    }

    public static void run(BookService bookService, ConsoleInputReader consoleInputReader) {
        CLIManager cliManager = new CLIManager(bookService, consoleInputReader);
        cliManager.makeCLI();
    }

    private void makeCLI() {
        System.out.println(WELCOME_MESSAGE);
        while (true) {
            printMenu();
            int commandKey = getValidCommandKey();
            printAdditionalLineWith(DASH_LINE);
            try {
                System.out.println("\n\t" + commands.get(commandKey).execute() + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println("\n\t" + e.getMessage() + "\n");
            } catch (ExitApplicationException e) {
                System.out.println("\t" + e.getMessage() + "\n");
                System.exit(0);
            }
        }
    }

    private static void printAdditionalLineWith(String text) {
        System.out.print(text);
    }

    private int getValidCommandKey() {
        try {
            return consoleInputReader.readValidCommandKey(commands.size());
        } catch (IllegalArgumentException e) {
            System.out.println(FOLLOW_INSTRUCTIONS_MESSAGE);
            return 0;
        }
    }

    private void printMenu() {
        commands.forEach((key, command) -> System.out.println("\t" + key + " - " + command.getDescription()));
        printAdditionalLineWith(USER_INPUT_MESSAGE);
    }
}
