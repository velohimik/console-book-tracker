package example.app;

import example.model.Book;
import example.service.BookService;
import example.service.impl.InMemoryBookService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        BookService bookService = new InMemoryBookService();

        System.out.println("\nWelcome to Your Library!!! Oops, let's be more quite. Please enter the relevant digit:\n");
        System.out.println("""
                \t1 - Add new book;
                \t2 - Look at the list of all books;
                \t3 - Search the book by its ID.""");

        while (!userChoice.equals("quit")) {
            System.out.println("\nIf you want exit the app please enter \"quit\"\n");
            System.out.print("\tYour choice: ");
            userChoice = scanner.nextLine();
            switch (userChoice) {
                case "quit":
                    break;
                case "1":
                    System.out.print("\tEnter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("\tEnter book author: ");
                    String bookAuthor = scanner.nextLine();
                    System.out.print("\tEnter the year book was written: ");
                    String bookYear = scanner.nextLine();
                    System.out.println("\n");
                    System.out.println(bookService.processNewBook(Book.createNewBook(bookTitle, bookAuthor, bookYear)));
                    break;
                case "2":
                    System.out.println(bookService.getAllBooks());
                    break;
                case "3":
                    System.out.print("\tEnter the book id you want to see: ");
                    String bookId = scanner.nextLine();
                    if (!bookId.isBlank()) {
                        System.out.println(bookService.findBookById(Integer.parseInt(bookId)));
                    } else {
                        System.out.println("Please enter the book ID");
                    }
                    break;
                default:
                    System.out.println("Please make the enter according to the instruction");
                    break;
            }
        }
    }
}
