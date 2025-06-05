package org.project.book.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.project.book.mapper.BookMapper;
import org.project.book.pojo.Book;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> {

    public Book queryByID(BigInteger id) {
        return super.getById(id);
    }

    public Book queryByISBN(String ISBN) {
        return super.getOne(Wrappers.<Book>lambdaQuery().eq(Book::getIsbn, ISBN));
    }

    public List<Book> queryByAnyKeyword(String keyword) {
        return super.getBaseMapper()
                .selectList(Wrappers.lambdaQuery(Book.class).like(Book::getTitle, keyword)
                        .or().like(Book::getAuthor, keyword).or().like(Book::getPublisher, keyword));
    }

    public List<Book> recommend() {
        long offset = (long) (getBookCount() * Math.random());
        return getBaseMapper().selectList(Wrappers.<Book>lambdaQuery().last("limit 10 offset " + offset));
    }

    public long getBookCount() {
        return getBaseMapper().selectCount(null);
    }
}
