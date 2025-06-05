package org.project.book.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.project.book.mapper.OrderMapper;
import org.project.book.pojo.Book;
import org.project.book.pojo.Order;
import org.project.book.pojo.OrderItem;
import org.project.book.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    public List<OrderVO> getCombinedOrderByUserID(BigInteger userID) {
        MPJLambdaWrapper<Order> wrapper = new MPJLambdaWrapper<Order>()
                .selectAs(Order::getId, OrderVO::getOrderID)
                .selectAs(Order::getStatus, OrderVO::getStatus)
                .selectAs(Order::getUserid, OrderVO::getUserID)
                .selectAs(Order::getDate, OrderVO::getTime)
                .selectAs(Order::getPrice, OrderVO::getPrice)
                .selectCollection(Book.class, OrderVO::getContent)
                .leftJoin(OrderItem.class, "`order_item`", OrderItem::getOrderid, Order::getId)
                .leftJoin(Book.class, "`book`", Book::getId, OrderItem::getBookid)
                .eq(Order::getUserid, userID);

        return getBaseMapper().selectJoinList(OrderVO.class, wrapper);
    }
}