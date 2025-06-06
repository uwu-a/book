package org.project.book.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.book.mapper.CartMapper;
import org.project.book.pojo.Book;
import org.project.book.pojo.Cart;
import org.project.book.pojo.User;
import org.project.book.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    BookService bookService;

    public CartVO getCartByUserID(BigInteger userID) {
        List<Cart> cartsList = lambdaQuery().eq(Cart::getUserid, userID)
                .select(Cart::getBookid,Cart::getAmount)
                .list();

        List<Book> bookList = new ArrayList<>();
        List<Integer> amountList = new ArrayList<>();
        for (Cart cart : cartsList) {
            bookList.add(bookService.lambdaQuery().eq(Book::getId, cart.getBookid()).one());
            amountList.add(cart.getAmount());
        }
        return CartVO.builder()
                .boosList(bookList)
                .amountsList(amountList)
                .build();
    }



}
