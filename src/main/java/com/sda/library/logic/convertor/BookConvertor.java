package com.sda.library.logic.convertor;

import com.sda.library.dto.BookDTO;
import com.sda.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookConvertor {

    @Autowired
    private AuthorConvertor authorConvertor;

    //this method convert books to booksDTO in order to send them to the controller
    public List<BookDTO> convertBookListToBookDTOList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(book.getTitle());
            bookDTO.setNumberOfPages(book.getNumberOfPages());
            bookDTO.setVolume(book.getVolume());
            bookDTO.setSection(book.getSection());
            bookDTO.setAuthorsDTO(this.authorConvertor.convertAuthorSetToAuthorDTOSet(book.getAuthors()));
            bookDTO.setAvailable(book.getAvailable());
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    public BookDTO convertBookToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setTitle(book.getTitle());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setVolume(book.getVolume());
        bookDTO.setSection(book.getSection());
        bookDTO.setAuthorsDTO(this.authorConvertor.convertAuthorSetToAuthorDTOSet(book.getAuthors()));
        bookDTO.setAvailable(book.getAvailable());

        return bookDTO;
    }

    public Book convertBookDTOToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAvailable(bookDTO.getAvailable());
        book.setAuthors(authorConvertor.convertAuthorDTOSetToAuthorSet(bookDTO.getAuthorsDTO()));
        book.setVolume(bookDTO.getVolume());
        book.setSection(bookDTO.getSection());
        book.setNumberOfPages(bookDTO.getNumberOfPages());

        return book;
    }

    public void updateBookFields(Book bookToUpdate, Book newBook) {
        bookToUpdate.setTitle(newBook.getTitle());
        bookToUpdate.setNumberOfPages(newBook.getNumberOfPages());
        bookToUpdate.setSection(newBook.getSection());
        bookToUpdate.setVolume(newBook.getVolume());
        bookToUpdate.setAuthors(newBook.getAuthors());
        bookToUpdate.setAvailable(newBook.getAvailable());
    }
}
