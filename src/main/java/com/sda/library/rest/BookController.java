package com.sda.library.rest;

import com.sda.library.dto.BookDTO;
import com.sda.library.exceptions.book.CountBooksException;
import com.sda.library.logic.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "/all")
    public List<BookDTO> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/author")
    public List<BookDTO> findBooksByAuthorSurname(@RequestParam String surname) {
        return bookService.findByAuthorSurname(surname);
    }

    @GetMapping(value = "/available")
    public List<BookDTO> findAllAvailable() {
        return bookService.findAvailable();
    }

    @GetMapping(value = "/title-volume-author")
    public BookDTO findByTitleAndVolumeAndAuthor (@RequestParam String title,
                                                  @RequestParam Integer volume,
                                                  @RequestParam String authorSurname) {
        return bookService.findByTitleAndVolumeAndAuthor(title, volume, authorSurname);
    }

    /**
     * Not working, problem with update authors
     */
    //from frontend should come all DTO parameters, because there are already there in order to modify
    //so all bookDTO parameters are there
    @PutMapping( path = "/update", consumes = "application/json")
    public ResponseEntity<String> updateBook(@RequestBody BookDTO bookDTO) {
        try {
            bookService.updateBook(bookDTO);
            return new ResponseEntity<String>("Update done!!", HttpStatus.OK);
        } catch (CountBooksException countEx) {
            return new ResponseEntity<String>(countEx.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
