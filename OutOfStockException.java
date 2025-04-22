package com.bookstore.exception;

public class OutOfStockException extends RuntimeException {
    private Long bookId;
    private int requestedQuantity;
    private int availableStock;

    public OutOfStockException(Long bookId, int requestedQuantity, int availableStock) {
        super("Book with ID " + bookId + " has only " + availableStock + 
              " items in stock, but " + requestedQuantity + " were requested.");
        this.bookId = bookId;
        this.requestedQuantity = requestedQuantity;
        this.availableStock = availableStock;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}