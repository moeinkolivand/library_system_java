package com.tutorial.library;

import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public List<Book> listAvailableBooks() {
        return List.of();
    };

    public void returnBook(String isbn, String memberId) {}

    public void borrowBook(String isbn, String memberId) {}
    public void registerMember(Member member) {}

    public void addBook(Book book) {}


}
