package com.velohimik.exceptions;

public class InvalidPublishedYearException extends IllegalArgumentException {

    public InvalidPublishedYearException(String message) {
        super(message);
    }
}
