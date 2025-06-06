package org.project.book.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.project.book.mapper.BookMapper;
import org.project.book.mapper.OrderItemMapper;
import org.project.book.mapper.OrderMapper;
import org.project.book.pojo.Book;
import org.project.book.pojo.Order;
import org.project.book.pojo.OrderItem;
import org.project.book.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    private final OrderItemMapper orderItemMapper;
    private final BookMapper bookMapper;

    @Autowired
    public OrderService(BookMapper bookMapper, OrderItemMapper orderItemMapper) {
        this.bookMapper = bookMapper;
        this.orderItemMapper = orderItemMapper;
    }

    public List<OrderVO> getCombinedOrderByUserID(BigInteger userID) {
        MPJLambdaWrapper<Order> wrapper = new MPJLambdaWrapper<Order>()
                .selectAs(Order::getId, OrderVO::getOrderID)
                .selectAs(Order::getStatus, OrderVO::getStatus)
                .selectAs(Order::getUserid, OrderVO::getUserID)
                .selectAs(Order::getDate, OrderVO::getTime)
                .selectAs(Order::getPrice, OrderVO::getPrice)
                .selectCollection(Book.class, OrderVO::getContent)
                .leftJoin(OrderItem.class, OrderItem::getOrderid, Order::getId)
                .leftJoin(Book.class, Book::getId, OrderItem::getBookid)
                .eq(Order::getUserid, userID);

        return getBaseMapper().selectJoinList(OrderVO.class, wrapper);
    }

    @Transactional
    public boolean createOrder(final List<OrderItem> orderItems, BigInteger userID) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            Book book = bookMapper.selectById(item.getBookid());

            if (book == null || book.getStock().compareTo(BigInteger.valueOf(item.getQuantity())) < 0)
                return false;

            if (bookMapper.updateById(Book.builder()
                    .id(book.getId()).version(book.getVersion())
                    .stock(book.getStock().subtract(BigInteger.valueOf(item.getQuantity()))).build()) == 0)
                throw new OptimisticLockingFailureException("");
            else
                total = total.add(book.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        Order createdOrder = Order.builder().status(0).price(total).userid(userID).date(new Date()).build();

        save(createdOrder);

        List<OrderItem> createdOrderItems = new ArrayList<>(orderItems);

        for (OrderItem orderItem : createdOrderItems)
            orderItem.setOrderid(createdOrder.getId());

        orderItemMapper.insert(createdOrderItems);
        return true;
    }

}