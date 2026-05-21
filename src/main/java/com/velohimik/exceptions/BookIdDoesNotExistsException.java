package com.velohimik.exceptions;

public class BookIdDoesNotExistsException extends IllegalArgumentException {

    public BookIdDoesNotExistsException(String message) {
        super(message);
    }
}
