package com.sda.library.repository;

import com.sda.library.config.HibernateUtil;
import com.sda.library.entities.Subscriber;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class SubscriberDAO {

    public List<Subscriber> findByNames(String firstName, String surname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select s from Subscriber s where s.firstName = " + firstName + " and s.surname = " + surname;
        Query query = session.createQuery(hql, Subscriber.class);
        List<Subscriber> subscriberList = query.getResultList();

        transaction.commit();
        session.close();
        return subscriberList;
    }

    public Subscriber findByPersonalID(Long personalID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select s from Subscriber s where s.personalID =" + personalID;
        Query query = session.createQuery(hql, Subscriber.class);
        Subscriber subscriber = (Subscriber) query.getSingleResult();

        transaction.commit();
        session.close();
        return subscriber;
    }

    public List<Subscriber> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select s from Subscriber s";
        Query query = session.createQuery(hql, Subscriber.class);
        List<Subscriber> subscriberList = query.getResultList();

        transaction.commit();
        session.close();
        return subscriberList;
    }

    public Subscriber findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select s from Subscriber s where id =" + id;
        Query query = session.createQuery(hql, Subscriber.class);
        Subscriber subscriber = (Subscriber) query.getSingleResult();

        transaction.commit();
        session.close();
        return subscriber;
    }
}
