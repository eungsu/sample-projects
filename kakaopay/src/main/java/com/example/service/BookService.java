package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.BookMapper;
import com.example.vo.Book;

@Service
public class BookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	public List<Book> getBooks() {
		return bookMapper.getAllBooks();
	}

	public Book getBook(int no) {
		return bookMapper.getBookByNo(no);
	}
	
	public void updateBook(Book book) {
		Book savedBook = getBook(book.getNo());
		savedBook.setDiscountPrice(book.getDiscountPrice());
		savedBook.setPrice(book.getPrice());
		savedBook.setStock(book.getStock());
		
		bookMapper.updateBook(savedBook);
	}
}
