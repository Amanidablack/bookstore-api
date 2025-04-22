package com.bookstore.exception;

public class CartNotFoundException extends RuntimeException {
    private Long customerId;

    public CartNotFoundException(Long customerId) {
        super("Cart for customer with ID " + customerId + " does not exist.");
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}