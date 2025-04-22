/*package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long customerId;
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(Long customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        // Check if the book is already in the cart
        for (CartItem existingItem : items) {
            if (existingItem.getBookId().equals(item.getBookId())) {
                // Update quantity if book already exists in cart
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // Add new item if book is not in cart
        items.add(item);
    }

    public void updateItem(Long bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void removeItem(Long bookId) {
        items.removeIf(item -> item.getBookId().equals(bookId));
    }

    public void clear() {
        items.clear();
    }
}*/

package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long id;  // Add this field for unique identification of each cart
    private Long customerId;
    private List<CartItem> items;

    // Default constructor
    public Cart() {
        this.items = new ArrayList<>();
    }

    // Constructor with customerId
    public Cart(Long customerId) {
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for customerId
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    // Getter and Setter for items
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Add an item to the cart
    public void addItem(CartItem item) {
        // Check if the book is already in the cart
        for (CartItem existingItem : items) {
            if (existingItem.getBookId().equals(item.getBookId())) {
                // Update quantity if the book already exists in the cart
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // Add new item if the book is not in the cart
        items.add(item);
    }

    // Update an item in the cart
    public void updateItem(Long bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    // Remove an item from the cart
    public void removeItem(Long bookId) {
        items.removeIf(item -> item.getBookId().equals(bookId));
    }

    // Clear the cart
    public void clear() {
        items.clear();
    }
}
