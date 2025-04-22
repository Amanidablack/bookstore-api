package com.bookstore.resource;

import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private final BookRepository bookRepository = new BookRepository();
    private final AuthorRepository authorRepository = new AuthorRepository();

    @POST
    public Response createBook(Book book, @Context UriInfo uriInfo) {
        validateBook(book);
        
        // Check if author exists
        if (!authorRepository.exists(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        
        Book savedBook = bookRepository.save(book);
        
        // Update author's books list
        authorRepository.findById(book.getAuthorId()).addBookId(savedBook.getId());
        
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedBook.getId())).build();
        return Response.created(location).entity(savedBook).build();
    }

    @GET
    public Response getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return Response.ok(books).build();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        Book book = bookRepository.findById(id);
        return Response.ok(book).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, Book book) {
        // Check if book exists
        bookRepository.findById(id);
        
        validateBook(book);
        
        // Check if author exists
        if (!authorRepository.exists(book.getAuthorId())) {
            throw new AuthorNotFoundException(book.getAuthorId());
        }
        
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        
        return Response.ok(updatedBook).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        Book book = bookRepository.findById(id);
        
        // Remove book from author's books list
        authorRepository.findById(book.getAuthorId()).removeBookId(id);
        
        bookRepository.delete(id);
        return Response.noContent().build();
    }
    
    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty");
        }
        
        if (book.getAuthorId() == null) {
            throw new InvalidInputException("Author ID cannot be null");
        }
        
        if (book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            throw new InvalidInputException("ISBN cannot be empty");
        }
        
        int currentYear = LocalDate.now().getYear();
        if (book.getPublicationYear() > currentYear) {
            throw new InvalidInputException("Publication year cannot be in the future");
        }
        
        if (book.getPrice() < 0) {
            throw new InvalidInputException("Price cannot be negative");
        }
        
        if (book.getStock() < 0) {
            throw new InvalidInputException("Stock cannot be negative");
        }
    }
}