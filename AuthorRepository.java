package com.bookstore.repository;

import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.model.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class AuthorRepository {
    private static final Map<Long, Author> authors = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(idGenerator.getAndIncrement());
        }
        authors.put(author.getId(), author);
        return author;
    }

    public Author findById(Long id) {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }
        return author;
    }

    public List<Author> findAll() {
        return new ArrayList<>(authors.values());
    }

    public void delete(Long id) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException(id);
        }
        authors.remove(id);
    }

    public boolean exists(Long id) {
        return authors.containsKey(id);
    }
}

/*NOT DAWG */