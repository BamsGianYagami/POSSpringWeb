package com.github.BamsGianYagami.POSSpringWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCart;
import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCartId;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId>{

    @Query("SELECT itemId FROM ShoppingCart WHERE username = :username")
    List<Integer> getListItemsByUsername(@Param("username") String username);
    
    @Query("SELECT S.itemId AS itemId, S.itemName AS itemName, S.itemPrice AS itemPrice, C.qty as qty, (S.itemPrice*C.qty) AS totalPrice FROM ShoppingCart C INNER JOIN Stock S ON C.itemId=S.itemId WHERE C.username=:username")
    List<ListCart> getListCartByUsername(@Param("username") String username);


    interface ListCart{
        Integer getItemId();
        String getItemName();
        Integer getItemPrice();
        Integer getQty();
        Integer getTotalPrice();
    }
}
