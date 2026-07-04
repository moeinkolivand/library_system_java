package com.tutorial.library;

public class MemberDoesNotExists extends RuntimeException {
    public MemberDoesNotExists(String message) {
        super(message);
    }
}
