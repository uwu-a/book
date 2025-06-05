package org.project.book.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.project.book.mapper.OrderMapper;
import org.project.book.pojo.Order;
import org.project.book.pojo.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    public List<Order> getOrdersByUserId(BigInteger userID) {
        MPJLambdaWrapper<Order> wrapper = new MPJLambdaWrapper<Order>()
                .selectAll(Order.class)
                .selectCollection(OrderItem.class, Order::getOrderItems)
                .leftJoin(OrderItem.class, OrderItem::getOrderid, Order::getId)
                .eq(Order::getUserid, userID);
        return getBaseMapper().selectJoinList(Order.class, wrapper);
    }
}
