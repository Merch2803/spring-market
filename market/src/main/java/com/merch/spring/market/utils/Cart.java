package com.merch.spring.market.utils;

import com.merch.spring.market.models.Product;
import com.merch.spring.market.models.ProductDTO;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Cart {
    private Set<ProductDTO> items;

    @PostConstruct
    public void init() {
        items = new TreeSet<>();
    }

    public Set<ProductDTO> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public void addProduct(Product product) {
        ProductDTO productDTO = convertToDTO(product);
        if (items.contains(productDTO)) {
            int count = items.stream().filter(t -> t.equals(productDTO)).findFirst().get().getCount();
            items.remove(productDTO);
            productDTO.setCount(count + 1);
        }
        items.add(productDTO);
    }

    public void clearCart() {
        items.clear();
    }

    public static ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .build();
    }
}

