package com.budiluhur.alreza.POSSpringWeb.Exception;

import com.budiluhur.alreza.POSSpringWeb.Entity.Book;

import lombok.Getter;

@Getter
public class DuplicateBookException extends RuntimeException {
    private final Book book;

    public DuplicateBookException(Book book) {
        this.book = book;
    }

    // getter methods
}
