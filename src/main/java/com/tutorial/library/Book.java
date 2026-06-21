package com.tutorial.library;

import java.util.Objects;

public class Book {
    private final String title;
    private final String isbn;
    private boolean isAvailable;
    private final String author;

    public Book(String title, String isbn, Boolean isAvailable, String author) {
        this.title = title;
        this.isbn = isbn;
        this.isAvailable = isAvailable == null || isAvailable;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getAuthor() {
        return author;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book Author is " + this.author + " The Title Is " + this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }
}
