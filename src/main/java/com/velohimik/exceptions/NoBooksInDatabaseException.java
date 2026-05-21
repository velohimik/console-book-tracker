package com.velohimik.exceptions;

public class NoBooksInDatabaseException extends RuntimeException {

    public NoBooksInDatabaseException(String message) {
        super(message);
    }
}
