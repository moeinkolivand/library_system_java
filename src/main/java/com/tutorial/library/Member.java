package com.tutorial.library;

import java.util.List;

public class Member {
    private final String memberId;
    private final String name;
    private List<Book> bookList;

    public Member(String memberId, String name, List<Book> bookList) {
        this.memberId = memberId;
        this.name = name;
        this.bookList = bookList;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void borrowBook() {}
    public void returnBook() {}
}
