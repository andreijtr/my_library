package com.sda.library.service;

import com.sda.library.dto.AuthorDTO;
import com.sda.library.entities.Author;
import com.sda.library.repository.AuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    private AuthorDAO authorDAO;

    //this method returns authorDTOs with given parameter, if exists
    public Set<AuthorDTO> getBySurname(String surname) {
        //fetch date from DB as list with authors
        List<Author> authorList = authorDAO.findBySurname(surname);
        //convert list to set of authors
        Set<Author> authorSet = convertAuthorListToAuthorSet(authorList);
        //convert authors to set of authorDTOs
        Set<AuthorDTO> authorDTOSet = convertAuthorSetToAuthorDTOSet(authorSet);
        return authorDTOSet;
    }

    //this method returns an Author entity for BookService and is used to find a book by an author surname
    Set<Author> findBySurname(String surname) {
        List<Author> authorList = authorDAO.findBySurname(surname);
        return convertAuthorListToAuthorSet(authorList);
    }

    //use this method to convert an author SET to an authorDTO SET, in order to send them to the controller
    //is necessary when converting a book to a bookDTO, because book contains a Set<Author> but bookDTO contains a Set<AuthorDTO>
    public Set<AuthorDTO> convertAuthorSetToAuthorDTOSet(Set<Author> authorSet) {
        Set<AuthorDTO> authorDTOSet = new HashSet<>();
        for(Author author : authorSet) {
            AuthorDTO authorDTO = new AuthorDTO(author.getFirstName(), author.getSurname(), author.getCountry());
            authorDTOSet.add(authorDTO);
        }
        return authorDTOSet;
    }

    //necessary when fetching date from DB, because they come as a List<Author> but a Book contains a Set<Author>
    public Set<Author> convertAuthorListToAuthorSet(List<Author> authorList) {
        Set<Author> authorSet = new HashSet<>();
        for(Author author : authorList) {
            authorSet.add(author);
        }
        return authorSet;
    }

    //flow to send an authors collection to FRONT-END:
    //1.fetch an authors List from DB
    //2.Convert list to set, in order to assign them to a book
    //3.when converting book to bookDTO, convert author SET to authorDTO set
}
