package com.sda.library.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "BOOKS_allBooks",
                    query = "select b from Book b join fetch b.authors"),
        @NamedQuery(name = "BOOKS_byAuthor",
                    query = "select b from Book b join fetch b.authors where :author in elements(b.authors)"),
        @NamedQuery(name = "BOOKS_allAvailable",
                    query = "select b from Book b join fetch b.authors where b.available = true"),
        @NamedQuery(name = "BOOKS_count",
                    query = "select count(b) from Book b where b.title = :title and b.volume = :volume"),
        @NamedQuery(name = "BOOKS_byTitleAndVolume",
                    query = "select b from Book b where b.title = :title and b.volume = :volume")
})

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "no_pages")
    private int numberOfPages;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "section")
    private String section;

    @Column(name = "available")
    private Boolean available;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "books_authors",
            joinColumns = { @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id")}
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookSubscriberBorrowingStart> borrowings = new HashSet<>();

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<BookSubscriberBorrowingStart> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(Set<BookSubscriberBorrowingStart> borrowings) {
        this.borrowings = borrowings;
    }

    //this way, app keeps synchronized the both sides of the association
    public void addAuthor(Author author){
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id &&
                numberOfPages == book.numberOfPages &&
                volume == book.volume &&
                title.equals(book.title) &&
                section.equals(book.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, numberOfPages, volume, section);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", volume=" + volume +
                ", section='" + section + '\'' +
                '}';
    }
}
