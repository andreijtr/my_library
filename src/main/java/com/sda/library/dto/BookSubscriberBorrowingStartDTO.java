package com.sda.library.dto;

import java.util.Date;
import java.util.Objects;

public class BookSubscriberBorrowingStartDTO {

    private BookDTO bookDTO;
    private SubscriberDTO subscriberDTO;
    private Date startBorrowingDate;

    public BookSubscriberBorrowingStartDTO() {
    }

    public BookSubscriberBorrowingStartDTO(BookDTO bookDTO, SubscriberDTO subscriberDTO, Date startBorrowingDate) {
        this.bookDTO = bookDTO;
        this.subscriberDTO = subscriberDTO;
        this.startBorrowingDate = startBorrowingDate;
    }

    @Override
    public String toString() {
        return "BookSubscriberBorrowingStartDTO{" +
                "bookDTO=" + bookDTO +
                ", subscriberDTO=" + subscriberDTO +
                ", startBorrowingDate=" + startBorrowingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSubscriberBorrowingStartDTO)) return false;
        BookSubscriberBorrowingStartDTO that = (BookSubscriberBorrowingStartDTO) o;
        return bookDTO.equals(that.bookDTO) &&
                subscriberDTO.equals(that.subscriberDTO) &&
                startBorrowingDate.equals(that.startBorrowingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookDTO, subscriberDTO, startBorrowingDate);
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public SubscriberDTO getSubscriberDTO() {
        return subscriberDTO;
    }

    public void setSubscriberDTO(SubscriberDTO subscriberDTO) {
        this.subscriberDTO = subscriberDTO;
    }

    public Date getStartBorrowingDate() {
        return startBorrowingDate;
    }

    public void setStartBorrowingDate(Date startBorrowingDate) {
        this.startBorrowingDate = startBorrowingDate;
    }
}
