package com.sda.library.service;

import com.sda.library.dto.BookDTO;
import com.sda.library.dto.BookSubscriberBorrowingStartDTO;
import com.sda.library.dto.SubscriberDTO;
import com.sda.library.entities.Book;
import com.sda.library.entities.BookSubscriberBorrowingStart;
import com.sda.library.entities.Subscriber;
import com.sda.library.repository.BorrowingStartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class BorrowingStartService {

    @Autowired
    private BorrowingStartDAO borrowingStartDAO;

    @Autowired
    private BookService bookService;

    @Autowired
    private SubscriberService subscriberService;

    public List<BookSubscriberBorrowingStartDTO> findAll() {
        List<BookSubscriberBorrowingStart> borrowingStartList = borrowingStartDAO.findAll();
        List<BookSubscriberBorrowingStartDTO> borrowingStartDTOList = new LinkedList<>();

        for (BookSubscriberBorrowingStart borrowingStart : borrowingStartList) {
            //get data from borrowingStart entity
            Book book = borrowingStart.getBook();
            Subscriber subscriber = borrowingStart.getSubscriber();
            Date startBorrowingDate = borrowingStart.getStartBorrowingDate();

            //convert entities in DTOs
            BookDTO bookDTO = bookService.convertBookToBookDTO(book);
            SubscriberDTO subscriberDTO = subscriberService.convertSubscriberToSubscriberDTO(subscriber);

            //create borrowingStartDTO
            BookSubscriberBorrowingStartDTO borrowingStartDTO = new BookSubscriberBorrowingStartDTO();
            borrowingStartDTO.setBookDTO(bookDTO);
            borrowingStartDTO.setSubscriberDTO(subscriberDTO);
            borrowingStartDTO.setStartBorrowingDate(startBorrowingDate);

            borrowingStartDTOList.add(borrowingStartDTO);
        }
        return borrowingStartDTOList;
    }

}
