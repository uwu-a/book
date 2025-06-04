package org.project.book.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.book.mapper.UserMapper;
import org.project.book.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
