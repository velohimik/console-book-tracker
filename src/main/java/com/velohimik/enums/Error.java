package com.velohimik.enums;

public enum Error {

    EXISTING_TITLE("The book with the current title is already existing"),
    INCORRECT_YEAR("The book's year is in the future"),
    INCORRECT_ID("The book with the current id does not exist");

    private final String errorDescription;

    Error(String description) {
        this.errorDescription = description;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
