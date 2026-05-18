package com.velohimik.model;

import java.time.Year;
import java.util.UUID;

public class Book {

    private final UUID id;
    private final String title;
    private final String author;
    private final Year year;

    private Book(String title, String author, Year year) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public static Book createNewBook(String title, String author, String year) {
        if (title != null && author != null && year != null) {
            return new Book(title, author, Year.parse(year));
        } else {
            throw new RuntimeException("Book can't be created with null fields");
        }
    }

    public String getId() {
        return id.toString();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("Book {\"id\": \"%s\", \"title\": \"%s\", \"author\": \"%s\", \"year\": \"%s\"}",
                id, title, author, year);
    }
}