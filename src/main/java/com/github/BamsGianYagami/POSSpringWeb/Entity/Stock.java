package com.github.BamsGianYagami.POSSpringWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    
    @Id
    int itemId;
    String itemName;
    Integer itemPrice;
    Float qty;
    String unitCount;
}
