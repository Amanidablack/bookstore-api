package com.bookstore.resource;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Customer;
import com.bookstore.repository.CustomerRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private final CustomerRepository customerRepository = new CustomerRepository();

    @POST
    public Response createCustomer(Customer customer, @Context UriInfo uriInfo) {
        validateCustomer(customer);
        
        Customer savedCustomer = customerRepository.save(customer);
        URI location = uriInfo.getAbsolutePathBuilder().path(String.valueOf(savedCustomer.getId())).build();
        return Response.created(location).entity(savedCustomer).build();
    }

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Customer customer = customerRepository.findById(id);
        return Response.ok(customer).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
        // Check if customer exists
        customerRepository.findById(id);
        
        validateCustomer(customer);
        
        customer.setId(id);
        Customer updatedCustomer = customerRepository.save(customer);
        
        return Response.ok(updatedCustomer).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerRepository.findById(id);
        customerRepository.delete(id);
        return Response.noContent().build();
    }

    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new InvalidInputException("Customer name cannot be empty");
        }

        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty");
        }
    }
}

/*NOT DAWG */