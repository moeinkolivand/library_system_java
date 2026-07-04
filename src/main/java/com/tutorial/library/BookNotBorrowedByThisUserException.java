package com.tutorial.library;

public class BookNotBorrowedByThisUserException extends RuntimeException {
    public BookNotBorrowedByThisUserException(String message) {
        super(message);
    }
}
