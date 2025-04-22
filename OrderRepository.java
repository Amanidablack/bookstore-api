package com.bookstore.repository;

import com.bookstore.exception.OrderNotFoundException;
import com.bookstore.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class OrderRepository {
    private static final Map<Long, Order> orders = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(idGenerator.getAndIncrement());
        }
        orders.put(order.getId(), order);
        return order;
    }

    public Order findById(Long id) {
        Order order = orders.get(id);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    public void delete(Long id) {
        if (!orders.containsKey(id)) {
            throw new OrderNotFoundException(id);
        }
        orders.remove(id);
    }

    public boolean exists(Long id) {
        return orders.containsKey(id);
    }
}

/*NOT DAWG */