package com.sda.library.service;

import com.sda.library.repository.BorrowingStartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingStartService {

    @Autowired
    private BorrowingStartDAO borrowingStartDAO;

    @Autowired
    private BookService bookService;

    @Autowired
    private SubscriberService subscriberService;

}
