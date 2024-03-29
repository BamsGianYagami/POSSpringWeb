package com.github.BamsGianYagami.POSSpringWeb.Exception;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Book;

import lombok.Getter;

@Getter
public class DuplicateBookException extends RuntimeException {
    private final Book book;

    public DuplicateBookException(Book book) {
        this.book = book;
    }

    // getter methods
}
