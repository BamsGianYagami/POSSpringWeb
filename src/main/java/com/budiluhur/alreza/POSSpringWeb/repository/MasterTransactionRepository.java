package com.budiluhur.alreza.POSSpringWeb.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.budiluhur.alreza.POSSpringWeb.Entity.MasterTransaction;

@Repository
public interface MasterTransactionRepository extends JpaRepository<MasterTransaction, Integer>{

    @Query("SELECT id, transactionTime, username, grandTotal FROM MasterTransaction")
    List<Summary> getAllSummary();
    
    @Query("SELECT id, transactionTime, username, grandTotal FROM MasterTransaction WHERE id=:id")
    List<Summary> getSummaryById(@Param("id") Integer id);

    interface Summary{
        Integer getId();
        Timestamp getTransactionTime();
        String getUsername();
        float getGrandTotal();
    }
}
