package com.sda.library.repository;

import com.sda.library.config.HibernateUtil;
import com.sda.library.entities.Book;
import com.sda.library.entities.BookSubscriberBorrowingStart;
import com.sda.library.entities.Subscriber;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class BorrowingStartDAO {

    public void saveBorrowing(BookSubscriberBorrowingStart borrowing) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(borrowing);

        transaction.commit();
        session.close();
    }

    public List<BookSubscriberBorrowingStart> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select b from BookSubscriberBorrowingStart b";
        Query query = session.createQuery(hql, BookSubscriberBorrowingStart.class);
        List<BookSubscriberBorrowingStart> borrowingStartList = query.getResultList();

        transaction.commit();
        session.close();
        return borrowingStartList;
    }

    public List<BookSubscriberBorrowingStart> findBySubscriber(Subscriber subscriber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select b from BookSubscriberBorrowingStart b where b.subscriber = " + subscriber;
        Query query = session.createQuery(hql, BookSubscriberBorrowingStart.class);
        List<BookSubscriberBorrowingStart> borrowingStartList = query.getResultList();

        transaction.commit();
        session.close();
        return borrowingStartList;
    }

    public List<BookSubscriberBorrowingStart> findByBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select b from BookSubscriberBorrowingStart b where b.book = " + book;
        Query query = session.createQuery(hql, BookSubscriberBorrowingStart.class);
        List<BookSubscriberBorrowingStart> borrowingStartList = query.getResultList();

        transaction.commit();
        session.close();
        return borrowingStartList;
    }

    public List<BookSubscriberBorrowingStart> findByDate(Date date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select b from BookSubscriberBorrowingStart b where b.startBorrowingDate = " + date;
        Query query = session.createQuery(hql, BookSubscriberBorrowingStart.class);
        List<BookSubscriberBorrowingStart> borrowingStartList = query.getResultList();

        transaction.commit();
        session.close();
        return borrowingStartList;
    }

    public List<BookSubscriberBorrowingStart> findByBookAndSubscriber(Book book, Subscriber subscriber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select b from BookSubscriberBorrowingStart b where " +
                 "b.book = " + book + " and b.subscriber = " + subscriber;
        Query query = session.createQuery(hql, BookSubscriberBorrowingStart.class);
        List<BookSubscriberBorrowingStart> borrowingStartList = query.getResultList();

        transaction.commit();
        session.close();
        return borrowingStartList;
    }

}
