package com.merch.spring.market.utils;

import com.merch.spring.market.models.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Cart {
    private Map<Product, Integer> items;

    @PostConstruct
    public void init() {
        items = new LinkedHashMap<>();
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addProduct(Product product) {
        items.put(product, items.getOrDefault(product, 0) + 1);
    }

    public void clearCart() {
        items.clear();
    }
}

