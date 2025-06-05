package org.project.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.project.book.pojo.Order;

@Mapper
public interface OrderMapper extends MPJBaseMapper<Order> {

}
