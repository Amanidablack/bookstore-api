package com.bookstore.repository;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class BookRepository {
    private static final Map<Long, Book> books = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(idGenerator.getAndIncrement());
        }
        books.put(book.getId(), book);
        return book;
    }

    public Book findById(Long id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public List<Book> findByAuthorId(Long authorId) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthorId().equals(authorId)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public void delete(Long id) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException(id);
        }
        books.remove(id);
    }

    public boolean exists(Long id) {
        return books.containsKey(id);
    }
}