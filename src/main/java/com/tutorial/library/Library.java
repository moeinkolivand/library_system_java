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
        Book book;
        Member member;
        boolean mmbrFounded = false;
        boolean bookFounded = false;
        for (Book bok : this.books) {
            if (bok.getIsbn().equals(isbn)) {
                if (!bok.isAvailable()) {
                    bookFounded = bok.getIsbn().equals(isbn);
                    book = bok;
                    for (Member mmbr : this.members) {
                        if (mmbr.getMemberId().equals(memberId)) {
                            member = mmbr;
                            mmbrFounded = true;
                            List<Book> memberBorrowedBooks = member.getborrowedBooks();
                            for (Book mmbrBook: memberBorrowedBooks) {
                                if (mmbrBook.getIsbn().equals(isbn)) {
                                    member.returnBook(book);
                                    bok.setAvailable(true);
                                    System.out.println("The Book With Isbn : " + isbn + " Returned");
                                    return;
                                }
                            }
                            throw new BookNotBorrowedByThisUser("Book Not Borrowed By This User !");
                        }
                    }
                    throw new MemberDoesNotExists("Member Does Not Exists");
                } else {
                    System.out.println("The Book Is Available And Not Borrowed By Any User");
                    return;
                }
            }
        }
        throw new BookDoesNotExists("Book Does Not Exists");
    }

    public void borrowBook(String isbn, String memberId) {
        Book book;
        Member member;
        boolean mmbrFounded = false;
        boolean bookFounded = false;
        for (Book bok : this.books) {
            if (bok.getIsbn().equals(isbn)) {
                if (bok.isAvailable()) {
                    bookFounded = bok.getIsbn().equals(isbn);
                    book = bok;
                    for (Member mmbr : this.members) {
                        if (mmbr.getMemberId().equals(memberId)) {
                            member = mmbr;
                            mmbrFounded = true;
                            List<Book> memberBorrowedBooks = member.getborrowedBooks();
                            if (memberBorrowedBooks.size() >= 3) {
                                throw new MaximumBorrowedBook("Member Already Borrowed Three Book");
                            }
                            for (Book mmbrBook: memberBorrowedBooks) {
                                if (mmbrBook.getIsbn().equals(isbn)) {
                                    System.out.println("Member With Id " + memberId + " " + "Already Have Book With Isbn: " + isbn);
                                    return;
                                }
                            }
                            member.borrowBook(book);
                            bok.setAvailable(false);
                        }
                    }
                    if (!mmbrFounded) {
                        throw new MemberDoesNotExists("Member Does Not Exists");
                    }
                } else {
                    throw new BookNotAvailable("Book Is Not Available");
                }
            }
        }
        if (!bookFounded) {
            throw new BookDoesNotExists("Book Does Not Exists");
        }
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
}
