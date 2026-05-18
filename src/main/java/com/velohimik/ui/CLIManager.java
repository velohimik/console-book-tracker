package com.velohimik.ui;

import com.velohimik.service.BookService;
import com.velohimik.validator.UserInputValidator;

import java.util.Scanner;

public class CLIManager {

    private static final String WELCOME_MESSAGE = "\nWelcome to Your Library!!! Oops, let's be more quite. Please enter the relevant digit:\n";
    private static final String MENU_MESSAGE = """
            \t1 - Add new book;
            \t2 - Look at the list of all books;
            \t3 - Search the book by its ID.""";
    private static final String QUIT_MESSAGE = "quit";
    private static final String BLOCK_SEPARATE = "============================";
    private static final String HOW_TO_EXIT_APP_MESSAGE = String.format("\nIf you want exit the app please enter \"%s\"\n", QUIT_MESSAGE);
    private static final String USER_INPUT_MESSAGE = "\tYour choice: ";
    private static final String MENU_FIRST_ITEM = "1";
    private static final String MENU_SECOND_ITEM = "2";
    private static final String MENU_THIRD_ITEM = "3";
    private static final String BOOK_TITLE = "\tEnter book title (can't be blank): ";
    private static final String BOOK_AUTHOR = "\tEnter book author (can't be blank): ";
    private static final String BOOK_YEAR = "\tEnter the year book was published (should be 4-digits number): ";
    private static final String BOOK_ID = "\tEnter the book id you want to see (should be a number): ";
    private static final String FOLLOW_INSTRUCTIONS_MESSAGE = "!!! Wrong input. Please make the enter according to the instruction:\n";
    private final BookService bookService;
    private final UserInputValidator userInputValidator;
    private final Scanner scanner;

    private CLIManager(BookService bookService, UserInputValidator validator, Scanner scanner) {
        this.bookService = bookService;
        this.userInputValidator = validator;
        this.scanner = scanner;
    }

    public static void run(BookService bookService, UserInputValidator userInputValidator, Scanner scanner) {
        CLIManager cliManager = new CLIManager(bookService, userInputValidator, scanner);
        cliManager.makeCLI();
    }

    private void makeCLI() {
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            System.out.println(MENU_MESSAGE);
            System.out.println(HOW_TO_EXIT_APP_MESSAGE);
            System.out.print(USER_INPUT_MESSAGE);
            String userChoice = getUserChoice();
            switch (userChoice) {
                case QUIT_MESSAGE:
                    System.exit(0);
                case MENU_FIRST_ITEM:
                    displayCreationOfNewBook();
                    break;
                case MENU_SECOND_ITEM:
                    System.out.println(bookService.getAllBooks());
                    break;
                case MENU_THIRD_ITEM:
                    displayBookDataById();
                    break;
                default:
                    System.out.println(FOLLOW_INSTRUCTIONS_MESSAGE);
                    break;
            }
        }
    }

    private String getUserChoice() {
        String userChoice = scanner.nextLine().trim();
        System.out.println(BLOCK_SEPARATE);
        return userChoice;
    }

    private void displayCreationOfNewBook() {
        String bookTitle = getTitleFromUserInput();
        String bookAuthor = getAuthorFromUserInput();
        String bookYear = getYearFromUserInput();
        System.out.println(bookService.saveNewBook(bookTitle, bookAuthor, bookYear));
    }

    private String getTitleFromUserInput() {
        String bookTitle;
        do {
            System.out.print(BOOK_TITLE);
            bookTitle = scanner.nextLine().trim();
        } while (userInputValidator.validateStringIsBlank(bookTitle));
        return bookTitle;
    }

    private String getAuthorFromUserInput() {
        String bookAuthor;
        do {
            System.out.print(BOOK_AUTHOR);
            bookAuthor = scanner.nextLine().trim();
        } while (userInputValidator.validateStringIsBlank(bookAuthor));
        return bookAuthor;
    }

    private String getYearFromUserInput() {
        String bookYear;
        do {
            System.out.print(BOOK_YEAR);
            bookYear = scanner.nextLine().trim();
        } while (!userInputValidator.validateYearIsFourDigits(bookYear));
        return bookYear;
    }

    private void displayBookDataById() {
        String bookId;
        do {
            System.out.print(BOOK_ID);
            bookId = scanner.nextLine().trim();
        } while (userInputValidator.validateIdIsNotNumeric(bookId));
        System.out.println(bookService.findBookById(bookId));
    }
}
