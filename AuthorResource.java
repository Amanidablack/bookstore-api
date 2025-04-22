package com.bookstore.resource;

import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Author;
import com.bookstore.repository.AuthorRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private final AuthorRepository authorRepository = new AuthorRepository();

    @POST
    public Response createAuthor(Author author, @Context UriInfo uriInfo) {
        validateAuthor(author);
        
        Author savedAuthor = authorRepository.save(author);
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedAuthor.getId())).build();
        return Response.created(location).entity(savedAuthor).build();
    }

    @GET
    public Response getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") Long id) {
        Author author = authorRepository.findById(id);
        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") Long id, Author author) {
        // Check if author exists
        authorRepository.findById(id);
        
        validateAuthor(author);
        
        author.setId(id);
        Author updatedAuthor = authorRepository.save(author);
        
        return Response.ok(updatedAuthor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") Long id) {
        Author author = authorRepository.findById(id);
        authorRepository.delete(id);
        return Response.noContent().build();
    }

    private void validateAuthor(Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty");
        }
    }
}

/*NOT DAWG */