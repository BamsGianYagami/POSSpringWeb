package com.github.BamsGianYagami.POSSpringWeb.services;

import java.util.Collection;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Book;

public interface BookService {
    Collection<Book> getBooks();
    Book addBook(Book book);
}
