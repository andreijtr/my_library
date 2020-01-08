package com.sda.library.repository;

import com.sda.library.config.HibernateUtil;
import com.sda.library.entities.Author;
import com.sda.library.entities.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDAO {

    public List<Book> findAllBooks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //I used FETCH TYPE LAZY, so i will use a join query to bound books and authors
        String hqlJoin = "select b from Book b join fetch b.authors";
        Query query = session.createQuery(hqlJoin);
        List<Book> booksQuery = query.getResultList();

        transaction.commit();
        session.close();
        return booksQuery;
    }

    public List<Book> findListByTitle(String titleWords) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String[] titleWordsSplitted = titleWords.split(" ");

        StringBuilder stringBuilder = new StringBuilder("%");
        for (String s : titleWordsSplitted) {
            stringBuilder.append(s + "%");
        }
        String hql = "select b from Book b where b.title like" + stringBuilder.toString();

        Query query = session.createQuery(hql, Book.class);
        List<Book> bookList = query.getResultList();

        transaction.commit();
        session.close();
        return bookList;
    }

    //this method find specific book by title, author, volume
    public Book findByTitleAndVolumeAndAuthor(String titleWords, Integer volume, Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        //1.build title from string
        String[] titleWordsSplitted = titleWords.split(" ");

        StringBuilder stringBuilderTitle = new StringBuilder("%");
        for (String s : titleWordsSplitted) {
            stringBuilderTitle.append(s + "%");
        }

        String hql;
        if (volume == null) {
            hql = "select b from Book b join fetch b.authors where b.title like '" + stringBuilderTitle.toString()
                    + "' and :author in elements(b.authors)";
        } else {
            hql = "select b from Book b join fetch b.authors where b.title like '" + stringBuilderTitle.toString()
                    + "' and :author in elements(b.authors) and b.volume = " + volume;
        }

        Query query = session.createQuery(hql, Book.class);
        query.setParameter("author", author);
        Book book = (Book) query.getSingleResult();

        transaction.commit();
        session.close();
        return book;
    }

    //this method returns ALL books with a specified author
    public List<Book> findByAuthor(Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query namedQuery = session.createNamedQuery("BOOKS_byAuthor", Book.class);
        namedQuery.setParameter("author", author);
        List<Book> bookList = namedQuery.getResultList();

        transaction.commit();
        session.close();
        return bookList;
    }

    public List<Book> findAllBooksAvailable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query namedQuery = session.createNamedQuery("BOOKS_allAvailable", Book.class);
        List<Book> bookList = namedQuery.getResultList();

        transaction.commit();
        session.close();
        return bookList;
    }

    public Book findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select b from Book b where id = " + id;
        Query query = session.createQuery(hql, Book.class);
        Book book = (Book) query.getSingleResult();

        transaction.commit();
        session.close();
        return book;
    }

    //UPDATE: nu mai e nevoie de metoda asta, pt ca la salvarea unui borrowing, se face automat update la book si subscriber
    //trebuie doar sa setezi valabilitatea cartii pe false
    //this method modifies book status, when it is borrowed or returned
    public void updateBookAvailability(Book book, boolean availability) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        book.setAvailable(availability);
        session.update(book);

        transaction.commit();
        session.close();
    }
}
