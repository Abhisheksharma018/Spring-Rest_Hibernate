package com.test.service;

import java.util.List;

import com.test.model.Book;

public interface BookService {
	
	public void save(Book book);
	public   Book get(long id);
	public  List<Book> list();
	public   void update(long id, Book book);
	public   void delete(long id);

}
