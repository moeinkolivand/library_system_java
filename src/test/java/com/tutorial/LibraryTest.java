package com.tutorial;

import com.tutorial.library.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Book bookOne;
    private Book bookTwo;
    private Book bookThree;
    private Book bookFour;
    private Member memberOne;
    private Member memberTwo;
    private Library library;
    private String bookOneIsbn = "1";
    private String memberOneId = "1";
    private String bookTwoIsbn = "2";
    private String memberTwoId = "2";

    @BeforeEach
    public void seedData() {
        bookOne = new Book("book one", bookOneIsbn, true, "moein");
        bookTwo = new Book("book two", bookTwoIsbn, true, "ali");
        bookThree = new Book("book three", "3", true, "alireza");
        bookFour = new Book("book four", "4", true, "ahmad");

        memberOne = new Member(memberOneId, "moein");
        memberTwo = new Member(memberTwoId, "ahmad");
        library = new Library();
        library.registerMember(memberOne);
        library.registerMember(memberTwo);
        library.addBook(bookOne);
        library.addBook(bookTwo);
        library.addBook(bookThree);
        library.addBook(bookFour);
    }

    @Test
    public void borrowBook() {
        library.borrowBook(bookOneIsbn, memberOneId);
        assertFalse(bookOne.isAvailable());
    }

    @Test
    public void returnBook() {
        library.borrowBook(bookOneIsbn, memberOneId);
        assertFalse(bookOne.isAvailable());
        library.returnBook(bookOneIsbn, memberOneId);
        assertTrue(bookOne.isAvailable());
    }

    @Test
    @DisplayName("Borrow Book When Its Not Available")
    public void borrowBookWhenItIsNotAvailable() {
        bookOne.setAvailable(false);
        assertThrowsExactly(BookNotAvailableException.class, () -> library.borrowBook(bookOneIsbn, memberOneId));
    }

    @Test
    @DisplayName("Borrow More Than Three Book For A Single User")
    public void borrowMoreThanThreeBookForUser() {
        library.borrowBook(bookOneIsbn, memberOneId);
        library.borrowBook(bookTwoIsbn, memberOneId);
        library.borrowBook("3", memberOneId);
        assertThrowsExactly(MaximumBorrowedBookException.class, () -> library.borrowBook("4", memberOneId));
    }

    @Test
    @DisplayName("Borrow Book For Not ExistedUser")
    public void borrowBookForNotExistedUser() {
        assertThrowsExactly(MemberNotFoundException.class, () -> library.borrowBook("1", "999999999999"));
    }

    @Test
    @DisplayName("Return Book That Doesnt Belong To Current User")
    public void returnBookDoesntBelongToCurrentUser() {
        library.borrowBook(bookOneIsbn, memberOneId);
        assertThrowsExactly(BookNotBorrowedByThisUserException.class, () -> library.returnBook(bookOneIsbn, memberTwoId));
    }

    @Test
    @DisplayName("Borrow Book Doesnt Exists")
    public void borrowBookDoesntExists() {
        assertThrowsExactly(BookNotFoundException.class, () -> library.borrowBook("132321231132321321", memberOneId));
    }

    @Test
    @DisplayName("Return Book Doesnt Exists")
    public void returnBookDoesntExists() {
        assertThrowsExactly(BookNotFoundException.class, () -> library.returnBook("132321231132321321", memberOneId));
    }
}
