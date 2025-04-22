/*package com.bookstore.resource;

import com.bookstore.exception.OrderNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderRepository orderRepository = new OrderRepository();

    @POST
    public Response createOrder(Order order, @Context UriInfo uriInfo) {
        validateOrder(order);
        
        Order savedOrder = orderRepository.save(order);
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedOrder.getId())).build();
        return Response.created(location).entity(savedOrder).build();
    }

    @GET
    public Response getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        Order order = orderRepository.findById(id);
        return Response.ok(order).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") Long id, Order order) {
        // Check if order exists
        orderRepository.findById(id);
        
        validateOrder(order);
        
        order.setId(id);
        Order updatedOrder = orderRepository.save(order);
        
        return Response.ok(updatedOrder).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderRepository.findById(id);
        orderRepository.delete(id);
        return Response.noContent().build();
    }

    private void validateOrder(Order order) {
        if (order.getCustomerId() == null) {
            throw new InvalidInputException("Customer ID cannot be null");
        }

        if (order.getBooks() == null || order.getBooks().isEmpty()) {
            throw new InvalidInputException("Order must contain at least one book");
        }
    }
}*/

/*NOT DAWG */

package com.bookstore.resource;

import com.bookstore.exception.OrderNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderRepository orderRepository = new OrderRepository();

    @POST
    public Response createOrder(Order order, @Context UriInfo uriInfo) {
        validateOrder(order);
        
        Order savedOrder = orderRepository.save(order);
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedOrder.getId())).build();
        return Response.created(location).entity(savedOrder).build();
    }

    @GET
    public Response getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        Order order = orderRepository.findById(id);
        return Response.ok(order).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") Long id, Order order) {
        // Check if order exists
        orderRepository.findById(id);
        
        validateOrder(order);
        
        order.setId(id);
        Order updatedOrder = orderRepository.save(order);
        
        return Response.ok(updatedOrder).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderRepository.findById(id);
        orderRepository.delete(id);
        return Response.noContent().build();
    }

    private void validateOrder(Order order) {
        if (order.getCustomerId() == null) {
            throw new InvalidInputException("Customer ID cannot be null");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new InvalidInputException("Order must contain at least one item");
        }
    }
}
