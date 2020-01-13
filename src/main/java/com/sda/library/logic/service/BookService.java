package com.sda.library.logic.service;

import com.sda.library.dto.BookDTO;
import com.sda.library.entities.Author;
import com.sda.library.entities.Book;
import com.sda.library.exceptions.book.CountBooksException;
import com.sda.library.repository.BookDAO;
import com.sda.library.logic.convertor.BookConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookConvertor bookConvertor;

    public List<BookDTO> findAllBooks() {
        List<Book> bookList = bookDAO.findAllBooks();
        List<BookDTO> bookDTOList = this.bookConvertor.convertBookListToBookDTOList(bookList);
        return bookDTOList;
    }

    public BookDTO findByTitleAndVolumeAndAuthor(String titleWords, Integer volume, String authorSurname) {
        Set<Author> authorSet = authorService.findBySurname(authorSurname);
        Book book = null;

        Iterator iterator = authorSet.iterator();
        while (iterator.hasNext() && book == null) {
            Author author = (Author) iterator.next();
            book = bookDAO.findByTitleAndVolumeAndAuthor(titleWords, volume, author);
        }
        return this.bookConvertor.convertBookToBookDTO(book);
    }

    //this method finds all books written by specified author
    //1.gets surname's author as parameter
    //2.calls authorService to find authors by surname
    //3.for each author found, search for books using private method findByAuthor(author)
    //4.convert books to booksDTO and return
    public List<BookDTO> findByAuthorSurname(String surname) {
        Set<Author> authorSet = authorService.findBySurname(surname);

        Iterator iterator = authorSet.iterator();
        List<BookDTO> bookDTOList = new ArrayList<>();
        while (iterator.hasNext()) {
            Author author = (Author) iterator.next();
            bookDTOList.addAll(findByAuthor(author));
        }
        return bookDTOList;
    }

    private List<BookDTO> findByAuthor(Author author) {
        List<Book> bookList = bookDAO.findByAuthor(author);
        List<BookDTO> bookDTOList = this.bookConvertor.convertBookListToBookDTOList(bookList);
        return bookDTOList;
    }

    //this method finds all available books(not borrowed)
    public List<BookDTO> findAvailable() {
        List<Book> bookList = bookDAO.findAllBooksAvailable();
        List<BookDTO> bookDTOList = this.bookConvertor.convertBookListToBookDTOList(bookList);
        return bookDTOList;
    }

    public void updateBook(BookDTO bookDTO) throws CountBooksException {
        Book book = bookConvertor.convertBookDTOToBook(bookDTO);
        bookDAO.updateBook(book);
    }
}
