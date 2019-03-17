package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.BookDao;
import com.test.model.Book;
import com.test.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService
{
	@Autowired
	   private BookDao bookDao;
	
		@Autowired
	   public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Transactional
	   @Override
	   public void save(Book book) {
	       bookDao.save(book);
	   }

	   @Override
	   public Book get(long id) {
	      return bookDao.get(id);
	   }

	   @Override
	   public List<Book> list() {
	      return bookDao.list();
	   }

	   @Transactional
	   @Override
	   public void update(long id, Book book) {
	      bookDao.update(id, book);
	   }

	   @Transactional
	   @Override
	   public void delete(long id) {
	      bookDao.delete(id);
	   }
}
