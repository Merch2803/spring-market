package com.merch.spring.market.controllers;

import com.merch.spring.market.models.Product;
import com.merch.spring.market.repositories.ProductRepository;
import com.merch.spring.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
    private final ProductRepository productRepository;

    @GetMapping("/add")
    public void addProductToCart(@RequestParam Long id) {
        Product product = productRepository.getById(id);
        cart.addProduct(product);
    }

    @GetMapping
    public List<Product> showAllProductsInCart() {
        return cart.getItems();
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clearCart();
        log.info("Cart was cleaned");
    }

}
