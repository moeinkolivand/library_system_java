package com.tutorial.library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String memberId;
    private final String name;
    private List<Book> borrowedBooks;

    public Member(String memberId, String name, List<Book> borrowedBooks) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = borrowedBooks;
    }

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<Book>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        this.borrowedBooks.add(book);
    }
    public void returnBook(Book book) {
        this.borrowedBooks.remove(book);
    }
}
