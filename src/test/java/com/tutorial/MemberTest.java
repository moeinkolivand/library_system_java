package com.tutorial;


import com.tutorial.library.Book;
import com.tutorial.library.Member;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;



public class MemberTest {
    private Book bookOne;
    private Book bookTwo;
    private Member memberOne;
    private Member memberTwo;

    @BeforeEach
    public void seedBook() {
        bookOne = new Book("book one", "1", true, "moein");
        bookTwo = new Book("book two", "2", true, "ali");
        memberOne = new Member("1", "moein");
        memberTwo = new Member("2", "ahmad");
    }

    @Test
    @DisplayName("Borrow Book Test")
    public void borrowBookTest() {
        memberOne.borrowBook(bookOne);
        assertEquals(1, memberOne.getBorrowedBooks().size());
        assertEquals(0, memberTwo.getBorrowedBooks().size());
        memberTwo.borrowBook(bookTwo);
        assertEquals(1,memberOne.getBorrowedBooks().size());
        assertEquals(1, memberTwo.getBorrowedBooks().size());
    }


    @Test
    @DisplayName("Return Book Test")
    public void returnBookTest() {
        memberOne.borrowBook(bookOne);
        assertEquals(1,memberOne.getBorrowedBooks().size());
        assertEquals(0, memberTwo.getBorrowedBooks().size());
        memberOne.returnBook(bookOne);
        assertEquals(0, memberOne.getBorrowedBooks().size());
        assertEquals(0, memberTwo.getBorrowedBooks().size());
    }
}
