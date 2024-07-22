package com.github.BamsGianYagami.POSSpringWeb.Entity;

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
@SequenceGenerator(name = "StockitemIdGenerator", sequenceName = "stock_sequence", allocationSize = 1)
public class Stock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StockitemIdGenerator")
    int itemId;
    String itemName;
    Integer itemPrice;
    Float qty;
    String unitCount;
}
