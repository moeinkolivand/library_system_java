package com.tutorial.library;

import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public void listAvailableBooks() {
        for (Book bok : this.books) {
            if (bok.isAvailable()) {
                System.out.println(bok);
            }
        }
    };

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
                            System.out.println("The Book Doesnt Borrowed For This User !");
                        }
                    }
                    if (!mmbrFounded) {
                        System.out.println("Member Does Not Exists !");
                        return;
                    }
                } else {
                    System.out.println("The Book Is Available And Not Borrowed By Any User");
                    return;
                }
            }
        }
        if (!bookFounded) {
            System.out.println("Book Does Not Exists !");
        }
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
                                System.out.println("Member Already Have 3 Books");
                            }
                            for (Book mmbrBook: memberBorrowedBooks) {
                                if (mmbrBook.getIsbn().equals(isbn)) {
                                    System.out.println("Member With Id " + memberId + " " + "Already Have Book With Isbn: " + isbn);
                                    return;
                                }
                                member.borrowBook(book);
                                bok.setAvailable(false);
                            }
                        }
                    }
                    if (!mmbrFounded) {
                        System.out.println("Member Does Not Exists !");
                        return;
                    }
                }
            }
        }
        if (!bookFounded) {
            System.out.println("Book Does Not Exists !");
        }
    }
    public void registerMember(Member member) {
        this.members.add(member);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }


}
