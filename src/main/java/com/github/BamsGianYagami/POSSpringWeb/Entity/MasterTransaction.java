package com.github.BamsGianYagami.POSSpringWeb.Entity;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToMany
    @JoinColumn(name="id", referencedColumnName = "id")
    Set<TransactionDetail> transactionDetails;

    public MasterTransaction(Timestamp transactionTime, String username, float grandTotal,
            Set<TransactionDetail> transactionDetails) {
        this.transactionTime = transactionTime;
        this.username = username;
        this.grandTotal = grandTotal;
        this.transactionDetails = transactionDetails;
    }

}
