package org.project.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.project.book.pojo.OrderItem;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
