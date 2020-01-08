package com.sda.library.repository;

import com.sda.library.config.HibernateUtil;
import com.sda.library.entities.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AuthorDAO {

    //this method return authors with specified SURNAME
    public List<Author> findBySurname(String surname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select a from Author a where lower(a.surname) like '%" + surname + "%'";
        Query query = session.createQuery(hql, Author.class);
        List<Author> authors = query.getResultList();

        transaction.commit();
        session.close();
        return authors;
    }
}
