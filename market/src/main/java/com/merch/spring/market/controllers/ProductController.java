package com.merch.spring.market.controllers;


import com.merch.spring.market.error_handling.MarketError;
import com.merch.spring.market.error_handling.ResourceNotFoundException;
import com.merch.spring.market.models.Product;
import com.merch.spring.market.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists: " + id));
    }

    @PostMapping
    public ResponseEntity<?> createNewProduct(@RequestBody Product product) {
        List<String> errors = new ArrayList<>();
        if (product.getTitle().length() < 3) {
            errors.add("Too short title");
        }
        if (product.getPrice() < 1) {
            errors.add("Invalid product price");
        }
        if (errors.size() > 0) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
        }
        Product out = productService.save(product);
        return new ResponseEntity<>(out, HttpStatus.CREATED);
    }

    @PutMapping
    public void update(@RequestBody Product product) {
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
