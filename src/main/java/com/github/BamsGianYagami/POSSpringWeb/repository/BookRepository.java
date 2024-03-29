package com.github.BamsGianYagami.POSSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
