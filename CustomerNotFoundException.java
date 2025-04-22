package com.bookstore.exception;

public class CustomerNotFoundException extends RuntimeException {
    private Long customerId;

    public CustomerNotFoundException(Long customerId) {
        super("Customer with ID " + customerId + " does not exist.");
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}