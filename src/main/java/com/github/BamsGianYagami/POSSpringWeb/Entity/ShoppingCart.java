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
@IdClass(ShoppingCartId.class)
public class ShoppingCart {
    
    @Id
    private Integer userId;

    @Id
    private Integer itemId;

    private Integer qty;
}