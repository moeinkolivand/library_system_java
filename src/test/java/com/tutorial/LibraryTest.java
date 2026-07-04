package com.tutorial;

import com.tutorial.library.Book;
import com.tutorial.library.Library;
import com.tutorial.library.Member;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Book bookOne;
    private Book bookTwo;
    private Member memberOne;
    private Member memberTwo;
    private Library library;

    @BeforeEach
    public void seedData() {
        bookOne = new Book("book one", "1", true, "moein");
        bookTwo = new Book("book two", "2", true, "ali");
        memberOne = new Member("1", "moein");
        memberTwo = new Member("2", "ahmad");
        library = new Library();
        library.registerMember(memberOne);
        library.registerMember(memberTwo);
        library.addBook(bookOne);
        library.addBook(bookTwo);
    }

    @Test
    public void borrowBook() {
        library.borrowBook("1", "1");
        assertFalse(bookOne.isAvailable());
    }

    @Test
    public void returnBook() {
        String isbn = "1";
        String memberId = "1";
        library.borrowBook(isbn, memberId);
        assertFalse(bookOne.isAvailable());
        library.returnBook(isbn, memberId);
        assertTrue(bookOne.isAvailable());
    }


}
