package com.merch.spring.market.utils;

import com.merch.spring.market.models.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Cart {
    private List<Product> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void clearCart() {
        items.clear();
    }
}

