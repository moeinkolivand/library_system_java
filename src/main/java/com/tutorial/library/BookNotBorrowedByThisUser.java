package com.tutorial.library;

public class BookNotBorrowedByThisUser extends RuntimeException {
    public BookNotBorrowedByThisUser(String message) {
        super(message);
    }
}
