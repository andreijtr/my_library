package com.sda.library.rest;

import com.sda.library.dto.BookSubscriberBorrowingStartDTO;
import com.sda.library.logic.service.BorrowingStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingStartService borrowingStartService;

    @GetMapping(value = "/all")
    public List<BookSubscriberBorrowingStartDTO> findAll() {
        return borrowingStartService.findAll();
    }
}
