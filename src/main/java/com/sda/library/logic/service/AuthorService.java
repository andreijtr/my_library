package com.sda.library.logic.service;

import com.sda.library.dto.AuthorDTO;
import com.sda.library.entities.Author;
import com.sda.library.repository.AuthorDAO;
import com.sda.library.logic.convertor.AuthorConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private AuthorConvertor authorConvertor;

    //this method returns authorDTOs with given parameter, if exists
    public Set<AuthorDTO> getBySurname(String surname) {
        //fetch date from DB as list with authors
        List<Author> authorList = authorDAO.findBySurname(surname);
        //convert list to set of authors
        Set<Author> authorSet = authorConvertor.convertAuthorListToAuthorSet(authorList);
        //convert authors to set of authorDTOs
        Set<AuthorDTO> authorDTOSet = authorConvertor.convertAuthorSetToAuthorDTOSet(authorSet);
        return authorDTOSet;
    }

    //this method returns an Author entity for BookService and is used to find a book by an author surname
    Set<Author> findBySurname(String surname) {
        List<Author> authorList = authorDAO.findBySurname(surname);
        return authorConvertor.convertAuthorListToAuthorSet(authorList);
    }

    //flow to send an authors collection to FRONT-END:
    //1.fetch an authors List from DB
    //2.Convert list to set, in order to assign them to a book
    //3.when converting book to bookDTO, convert author SET to authorDTO set
}
