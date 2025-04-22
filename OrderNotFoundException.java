package com.bookstore.exception;

public class OrderNotFoundException extends RuntimeException {
    private Long orderId;

    public OrderNotFoundException(Long orderId) {
        super("Order with ID " + orderId + " does not exist.");
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
