package com.github.BamsGianYagami.POSSpringWeb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(TransactionDetailId.class)
public class TransactionDetail {
    @Id
    Integer id;

    @Id
    Integer itemId;
    
    String itemName;
    float qty;
    float itemPrice;
    float totalPrice;
    
    public TransactionDetail(Integer itemId, String itemName, float qty, float itemPrice, float totalPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.qty = qty;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
    }

}
