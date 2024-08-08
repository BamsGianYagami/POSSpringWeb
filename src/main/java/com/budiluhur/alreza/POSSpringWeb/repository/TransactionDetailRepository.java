package com.budiluhur.alreza.POSSpringWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.budiluhur.alreza.POSSpringWeb.Entity.TransactionDetail;
import com.budiluhur.alreza.POSSpringWeb.Entity.TransactionDetailId;


@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, TransactionDetailId>{
    
    @Query("SELECT T.id AS id, T.itemId AS itemId, S.itemName AS itemName, T.qty as qty, S.itemPrice AS itemPrice, T.totalPrice AS totalPrice FROM TransactionDetail T INNER JOIN Stock S ON T.itemId=S.itemId where T.id = :id")
    List<listTransactionDetail> getGransactionDetailById(@Param("id") Integer id);

    interface listTransactionDetail {
        Integer getId();
        Integer getItemId();
        String getItemName();
        float getQty();
        float getItemPrice();
        float getTotalPrice();
    }
}
