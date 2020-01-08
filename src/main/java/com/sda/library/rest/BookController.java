package com.sda.library.rest;

import com.sda.library.dto.BookDTO;
import com.sda.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseBody
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
}
