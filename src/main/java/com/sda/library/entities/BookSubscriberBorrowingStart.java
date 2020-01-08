package com.sda.library.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

//n ai termiant clasa asta
//relatia intre book si clasa asta e one to many
//relatia intre book si subscriber e many to mant prin clasa asta
//relatia dintre subscriber si clasa e one to many

@Entity
@Table(name = "book_subscriber_borrowing_start")
public class BookSubscriberBorrowingStart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @Column(name = "start_borrow_data")
    private Date startBorrowingDate;

    public BookSubscriberBorrowingStart() {
    }

    public BookSubscriberBorrowingStart(Book book, Subscriber subscriber, Date startBorrowingDate) {
        this.book = book;
        this.subscriber = subscriber;
        this.startBorrowingDate = startBorrowingDate;
    }

    @Override
    public String toString() {
        return "BookSubscriberBorrowing{" +
                "id=" + id +
                ", book=" + book +
                ", subscriber=" + subscriber +
                ", startBorrowing=" + startBorrowingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSubscriberBorrowingStart)) return false;
        BookSubscriberBorrowingStart that = (BookSubscriberBorrowingStart) o;
        return id.equals(that.id) &&
                book.equals(that.book) &&
                subscriber.equals(that.subscriber) &&
                startBorrowingDate.equals(that.startBorrowingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, subscriber, startBorrowingDate);
    }

    public Integer getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Date getStartBorrowingDate() {
        return startBorrowingDate;
    }

    public void setStartBorrowingDate(Date startBorrowingDate) {
        this.startBorrowingDate = startBorrowingDate;
    }
}
