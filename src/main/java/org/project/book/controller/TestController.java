package org.project.book.controller;

import org.project.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class TestController {
    @Autowired
    private BookService bookService;

    @GetMapping("/test")
    public Object test(BigInteger p) {
        return bookService.queryByID(p);
    }
}
