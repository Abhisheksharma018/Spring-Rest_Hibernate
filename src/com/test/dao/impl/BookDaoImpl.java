package com.test.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.test.dao.BookDao;
import com.test.model.Book;

@Repository
public class BookDaoImpl implements BookDao
{
	 @Autowired
	   private SessionFactory sessionFactory;
	 

	   public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	   public void save(Book book) {
	     getSession().saveOrUpdate(book);
	   }

	   @Override
	   public Book get(long id) {
	      return getSession().get(Book.class, id);
	   }

	   @SuppressWarnings("unchecked")
	@Override
	   public List<Book> list() {
	     /* Session session = sessionFactory.getCurrentSession();
	      CriteriaBuilder cb = session.getCriteriaBuilder();
	      CriteriaQuery<Book> cq = cb.createQuery(Book.class);
	      Root<Book> root = cq.from(Book.class);
	      cq.select(root);
	      Query<Book> query = session.createQuery(cq);
	      return query.getResultList();*/
		   @SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(Book.class);
		   return  (List<Book>)criteria.list();
	   }

	   @Override
	   public void update(long id, Book book) {
	      Session session = getSession();
	      Book book2 = session.byId(Book.class).load(id);
	      book2.setTitle(book.getTitle());
	      book2.setAuthor(book.getAuthor());
	      session.flush();
	   }

	   @Override
	   public void delete(long id) {
	      Session session = getSession();
	      Book book = session.byId(Book.class).load(id);
	      session.delete(book);
	   }
}
