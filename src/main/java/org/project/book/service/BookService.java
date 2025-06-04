package org.project.book.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.book.mapper.BookMapper;
import org.project.book.pojo.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> {

}
