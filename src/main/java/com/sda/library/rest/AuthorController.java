package com.sda.library.rest;

import com.sda.library.dto.AuthorDTO;
import com.sda.library.repository.AuthorDAO;
import com.sda.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping()
    @ResponseBody
    public Set<AuthorDTO> getAuthorBySurname(@RequestParam String surname) {
        return authorService.getBySurname(surname);
    }

}
