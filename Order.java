package com.bookstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Long customerId;
    private List<CartItem> items;
    private Date orderDate;
    private double totalAmount;

    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = new Date();
    }

    public Order(Long id, Long customerId, List<CartItem> items, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.orderDate = new Date();
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}