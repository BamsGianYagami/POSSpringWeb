package com.budiluhur.alreza.POSSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.budiluhur.alreza.POSSpringWeb.Entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{
}
