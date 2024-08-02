package com.github.BamsGianYagami.POSSpringWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.BamsGianYagami.POSSpringWeb.Entity.TransactionDetail;
import com.github.BamsGianYagami.POSSpringWeb.Entity.TransactionDetailId;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, TransactionDetailId>{
    
}
