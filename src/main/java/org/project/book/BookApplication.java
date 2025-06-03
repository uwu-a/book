package org.project.book;

import org.project.book.pojo.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		new Book().setId(new BigInteger("1"));
		SpringApplication.run(BookApplication.class, args);
	}

}
