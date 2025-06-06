package org.project.book.controller;

import org.project.book.service.CartService;
import org.project.book.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class CartDataController {
    private final CartService cartService;

    @Autowired
    public CartDataController(CartService cartService) {
        this.cartService = cartService;
    }
        @GetMapping("/cart")
        public CartVO getCarts(@RequestParam BigInteger userID) {
        return cartService.getCartByUserID(userID);
        }
}