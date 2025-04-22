package com.bookstore;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import com.bookstore.mapper.*;
import com.bookstore.resource.*;

@ApplicationPath("/api")
public class BookstoreApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        // Resources
        classes.add(BookResource.class);
        classes.add(AuthorResource.class);
        classes.add(CustomerResource.class);
        classes.add(CartResource.class);
        classes.add(OrderResource.class);
        
        // Exception mappers
        classes.add(BookNotFoundExceptionMapper.class);
        classes.add(AuthorNotFoundExceptionMapper.class);
        classes.add(CustomerNotFoundExceptionMapper.class);
        classes.add(InvalidInputExceptionMapper.class);
        classes.add(OutOfStockExceptionMapper.class);
        classes.add(CartNotFoundExceptionMapper.class);
        
        return classes;
    }
}