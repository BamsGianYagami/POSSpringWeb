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
    
    @Query("SELECT T FROM TransactionDetail T where T.id = :id")
    List<TransactionDetail> getGransactionDetailById(@Param("id") Integer id);
}
