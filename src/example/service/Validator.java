package example.service;

import example.enums.Error;
import example.model.Book;

import java.util.List;

public interface Validator {
    List<Error> validate(Book book);
}
