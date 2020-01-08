package com.sda.library.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "cnp")
    private Long personalID;

    @OneToMany(mappedBy = "subscriber")
    private Set<BookSubscriberBorrowingStart> borrowings = new HashSet<>();

    public Subscriber() {
    }

    public Subscriber(String firstName, String surname, Long personalID) {
        this.firstName = firstName;
        this.surname = surname;
        this.personalID = personalID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscriber)) return false;
        Subscriber that = (Subscriber) o;
        return id.equals(that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(surname, that.surname) &&
                personalID.equals(that.personalID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, personalID);
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", personalID=" + personalID +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Long personalID) {
        this.personalID = personalID;
    }

    public Set<BookSubscriberBorrowingStart> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(Set<BookSubscriberBorrowingStart> borrowings) {
        this.borrowings = borrowings;
    }
}
