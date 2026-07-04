package com.tutorial.library;

public class BookDoesNotExistsException extends RuntimeException {
    public BookDoesNotExistsException(String message) {
        super(message);
    }
}
