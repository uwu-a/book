package org.project.book.controller;

import org.project.book.pojo.Cart;
import org.project.book.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
public class CartDataController {
    private final CartService cartService;
    @Autowired
    public CartDataController(CartService cartService) {
        this.cartService = cartService;
    }

}