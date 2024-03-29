package com.github.BamsGianYagami.POSSpringWeb.services.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Book;
import com.github.BamsGianYagami.POSSpringWeb.Exception.DuplicateBookException;
import com.github.BamsGianYagami.POSSpringWeb.repository.BookRepository;
import com.github.BamsGianYagami.POSSpringWeb.services.BookService;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public Collection<Book> getBooks() {
        Collection<Book> collectBook = bookRepository.findAll();
        return collectBook;
    }

    @Override
    public Book addBook(Book book) {
        final Optional<Book> existingBook = bookRepository.findById(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new DuplicateBookException(book);
        }

        return bookRepository.save(book);
    }
    
}
