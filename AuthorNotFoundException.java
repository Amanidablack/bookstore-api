package com.bookstore.exception;

public class AuthorNotFoundException extends RuntimeException {
    private Long authorId;

    public AuthorNotFoundException(Long authorId) {
        super("Author with ID " + authorId + " does not exist.");
        this.authorId = authorId;
    }

    public Long getAuthorId() {
        return authorId;
    }
}