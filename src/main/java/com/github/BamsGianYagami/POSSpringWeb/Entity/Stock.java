package com.github.BamsGianYagami.POSSpringWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
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
