package com.test.dao;

import java.util.List;

import com.test.model.Book;

public interface BookDao {
	
	   void save(Book book);
	   Book get(long id);
	   List<Book> list();
	   void update(long id, Book book);
	   void delete(long id);

}
