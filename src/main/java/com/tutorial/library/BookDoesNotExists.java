package com.tutorial.library;

public class BookDoesNotExists extends RuntimeException {
    public BookDoesNotExists(String message) {
        super(message);
    }
}
