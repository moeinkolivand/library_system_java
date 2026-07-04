package com.tutorial.library;

public class BookNotAvailable extends RuntimeException {
    public BookNotAvailable(String message) {
        super(message);
    }
}
