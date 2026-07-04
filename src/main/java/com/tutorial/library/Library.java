package com.tutorial.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library(List<Book> books, List<Member> members) {
        this.books = books;
        this.members = members;
    }

    public Library() {
        this.books = new ArrayList<Book>();
        this.members = new ArrayList<Member>();
    }

    public void listAvailableBooks() {
        for (Book bok : this.books) {
            if (bok.isAvailable()) {
                System.out.println(bok);
            }
        }
    }

    public void returnBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);
        if (!member.getBorrowedBooks().contains(book)) {
            throw new BookNotBorrowedByThisUserException("Book With Isbn " + isbn + "Dosnt Borrowed By This User !");
        }
        member.returnBook(book);
        book.setAvailable(true);
        System.out.println("Book '" + isbn + "' returned by member '" + memberId + "'");
    }

    public void borrowBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book With Isbn" + isbn + "Is Not Available");
        }
        if (member.getBorrowedBooks().size() >= 3) {
            throw new MaximumBorrowedBookException("Member '" + memberId + "' has reached the 3-book limit");
        }
        if (member.getBorrowedBooks().contains(book)) {
            System.out.println("Member already has this book");
            return;
        }
        member.borrowBook(book);
        book.setAvailable(false);
        System.out.println("Book '" + isbn + "' borrowed by member '" + memberId + "'");
    }
    public void registerMember(Member member) {
        for (Member mmbr: this.members) {
            if (mmbr.equals(member)) {
                System.out.println("Member Already Add it");
                return;
            }
        }
        this.members.add(member);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    private Book findBookByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElseThrow(() -> new BookNotFoundException("Book With Isbn " + isbn + "Not Founded"));
    }

    private Member findMemberById(String id) {
        return members.stream().filter(member -> member.getMemberId().equals(id)).findFirst().orElseThrow(() -> new MemberNotFoundException("Member With Id " + id + "Does Not Exists"));
    }
}
