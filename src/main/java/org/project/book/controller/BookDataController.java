package org.project.book.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.project.book.pojo.Book;
import org.project.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookDataController {
    private final BookService bookService;

    @Autowired
    public BookDataController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/id")
    @Operation(description = "按ID查询一本书")
    public Book getBookById(@RequestParam BigInteger ID) {
        return bookService.queryByID(ID);
    }

    @GetMapping("/isbn")

    public Book getBookByISBN(@RequestParam String ISBN) {
        return bookService.queryByISBN(ISBN);
    }

    @GetMapping("/query")

    public List<Book> getBookByTitle(@RequestParam String key) {
        return bookService.queryByAnyKeyword(key);
    }

    @GetMapping("/recommend")

    public List<Book> getBookByRecommend() {
        return bookService.recommend();
    }
}
