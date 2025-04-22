package com.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
    private Long bookId;

    public BookNotFoundException(Long bookId) {
        super("Book with ID " + bookId + " does not exist.");
        this.bookId = bookId;
    }

    public Long getBookId() {
        return bookId;
    }
}