package com.bookstore.repository;

import com.bookstore.exception.CartNotFoundException;
import com.bookstore.model.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CartRepository {
    private static final Map<Long, Cart> carts = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public Cart save(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(idGenerator.getAndIncrement());
        }
        carts.put(cart.getId(), cart);
        return cart;
    }

    public Cart findById(Long id) {
        Cart cart = carts.get(id);
        if (cart == null) {
            throw new CartNotFoundException(id);
        }
        return cart;
    }

    public List<Cart> findAll() {
        return new ArrayList<>(carts.values());
    }

    public void delete(Long id) {
        if (!carts.containsKey(id)) {
            throw new CartNotFoundException(id);
        }
        carts.remove(id);
    }

    public boolean exists(Long id) {
        return carts.containsKey(id);
    }
}


/*NOT DAWG */