package com.velohimik.exceptions;

public class ExistingBookTitleException extends IllegalArgumentException {

    public ExistingBookTitleException(String message) {
        super(message);
    }
}
