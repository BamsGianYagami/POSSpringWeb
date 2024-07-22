package com.github.BamsGianYagami.POSSpringWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCart;
import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCartId;

@Repository
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart, ShoppingCartId>{

    @Query("SELECT itemId FROM ShoppingCart WHERE userId = :userId")
    List<Integer> getListItemsByUserId(@Param("userId") Integer userId);
    
}
