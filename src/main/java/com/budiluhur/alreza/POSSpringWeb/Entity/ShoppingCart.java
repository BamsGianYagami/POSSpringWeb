package com.budiluhur.alreza.POSSpringWeb.Entity;

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
@IdClass(ShoppingCartId.class)
public class ShoppingCart {
    
    @Id
    private String username;

    @Id
    private Integer itemId;

    private float qty;

    public void reduceQty(float amount){
        qty -= amount;
    }
}
