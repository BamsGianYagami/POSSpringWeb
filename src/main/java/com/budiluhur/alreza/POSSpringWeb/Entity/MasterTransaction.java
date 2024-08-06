package com.budiluhur.alreza.POSSpringWeb.Entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "MasterTransactionIdGenerator", sequenceName = "MASTER_TRANSACTION_SEQUENCE", allocationSize = 1)
public class MasterTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MasterTransactionIdGenerator")
    Integer id;
    Timestamp transactionTime;
    String username;
    float grandTotal;

    public MasterTransaction(Timestamp transactionTime, String username) {
        this.transactionTime = transactionTime;
        this.username = username;
    }
}
