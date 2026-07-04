package com.tutorial.library;

public class MemberDoesNotExistsException extends RuntimeException {
    public MemberDoesNotExistsException(String message) {
        super(message);
    }
}
