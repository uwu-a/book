package org.project.book.controller;

import org.project.book.pojo.OrderItem;
import org.project.book.service.OrderService;
import org.project.book.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public OrderVO getOrder(@RequestParam BigInteger userID) {
        return orderService.getCombinedOrderByUserID(userID);
    }

    @PostMapping("/generate")
    public void generateOrder(@RequestBody List<OrderItem> orderItems) {
        orderService.createOrder(orderItems,new BigInteger("1")); //TODO
    }
}
