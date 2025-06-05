package org.project.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.project.book.pojo.Cart;
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}
