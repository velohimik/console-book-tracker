package example.model;

import java.time.Year;

public class Book {

    private static int idCounter = 1;

    private final Integer id;
    private final String title;
    private final String author;
    private final Year year;

    private Book(String title, String author, Year year) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public static Book createNewBook(String title, String author, String year) {
        return new Book(title, author, Year.parse(year));
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Year getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("Book {\"id\": \"%s\", \"title\": \"%s\", \"author\": \"%s\", \"year\": \"%s\"}",
                id, title, author, year);
    }
}