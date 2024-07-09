package com.github.BamsGianYagami.POSSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{
}
