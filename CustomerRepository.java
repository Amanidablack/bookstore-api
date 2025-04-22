package com.bookstore.repository;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerRepository {
    private static final Map<Long, Customer> customers = new HashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(idGenerator.getAndIncrement());
        }
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer findById(Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    public void delete(Long id) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException(id);
        }
        customers.remove(id);
    }

    public boolean exists(Long id) {
        return customers.containsKey(id);
    }
}


/*NOT DAWG */