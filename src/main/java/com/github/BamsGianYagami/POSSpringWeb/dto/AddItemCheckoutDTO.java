package com.github.BamsGianYagami.POSSpringWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemCheckoutDTO {
    int itemId;
    String itemName;
    String itemPrice;
    int qty;
    int totalPrice;
}
