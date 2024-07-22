package com.github.BamsGianYagami.POSSpringWeb.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartId implements Serializable{
    private int userId;
    private int itemId;
}
