package org.project.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.project.book.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
