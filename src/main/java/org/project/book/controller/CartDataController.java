package org.project.book.controller;

import org.project.book.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartDataController {
    private final CartService cartService;

    @Autowired
    public CartDataController(CartService cartService) {
        this.cartService = cartService;
    }

}