package com.budiluhur.alreza.POSSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.budiluhur.alreza.POSSpringWeb.Entity.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
