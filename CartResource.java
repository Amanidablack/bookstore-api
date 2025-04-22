package com.bookstore.resource;

import com.bookstore.exception.CartNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Cart;
import com.bookstore.repository.CartRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CartRepository cartRepository = new CartRepository();

    @POST
    public Response createCart(Cart cart, @Context UriInfo uriInfo) {
        validateCart(cart);
        
        Cart savedCart = cartRepository.save(cart);
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedCart.getId())).build();
        return Response.created(location).entity(savedCart).build();
    }

    @GET
    public Response getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return Response.ok(carts).build();
    }

    @GET
    @Path("/{id}")
    public Response getCartById(@PathParam("id") Long id) {
        Cart cart = cartRepository.findById(id);
        return Response.ok(cart).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCart(@PathParam("id") Long id, Cart cart) {
        // Check if cart exists
        cartRepository.findById(id);
        
        validateCart(cart);
        
        cart.setId(id);
        Cart updatedCart = cartRepository.save(cart);
        
        return Response.ok(updatedCart).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCart(@PathParam("id") Long id) {
        cartRepository.findById(id);
        cartRepository.delete(id);
        return Response.noContent().build();
    }

    private void validateCart(Cart cart) {
        if (cart.getCustomerId() == null) {
            throw new InvalidInputException("Customer ID cannot be null");
        }
    }
}

/*NOT DAWG */