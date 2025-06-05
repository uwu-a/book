package org.project.book.controller;

import org.project.book.pojo.Order;
import org.project.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderDataController {
    private final OrderService orderService;
    @Autowired
    public OrderDataController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get")
    public List<Order> getOrder(@RequestParam BigInteger userID) {
        return orderService.getOrdersByUserId(userID);
    }
}
