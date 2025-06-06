package org.project.book.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.book.mapper.CartMapper;
import org.project.book.pojo.Cart;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {
    public Cart getCartByUserID(BigInteger userID) {
        return getBaseMapper().selectOne(Wrappers.<Cart>lambdaQuery().eq(Cart::getUserid, userID));
    }

}
