package com.sda.library.logic.convertor;

import com.sda.library.dto.AuthorDTO;
import com.sda.library.entities.Author;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthorConvertor {

    //use this method to convert an author SET to an authorDTO SET, in order to send them to the controller
    //is necessary when converting a book to a bookDTO, because book contains a Set<Author> but bookDTO contains a Set<AuthorDTO>
    public Set<AuthorDTO> convertAuthorSetToAuthorDTOSet(Set<Author> authorSet) {
        Set<AuthorDTO> authorDTOSet = new HashSet<>();
        for(Author author : authorSet) {
            AuthorDTO authorDTO = convertAuthorToAuthorDTO(author);
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

    public AuthorDTO convertAuthorToAuthorDTO (Author author) {
        return new AuthorDTO(author.getFirstName(), author.getSurname(), author.getCountry());
    }

    public Set<Author> convertAuthorDTOSetToAuthorSet(Set<AuthorDTO> authorDTOSet) {
        Set<Author> authorSet = new HashSet<>();
        for (AuthorDTO authorDTO : authorDTOSet) {
            authorSet.add(this.convertAuthorDTOToAuthor(authorDTO));
        }
        return authorSet;
    }

    public Author convertAuthorDTOToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setSurname(authorDTO.getSurname());
        author.setFirstName(authorDTO.getFirstName());
        author.setCountry(authorDTO.getCountry());
        return author;
    }
}
