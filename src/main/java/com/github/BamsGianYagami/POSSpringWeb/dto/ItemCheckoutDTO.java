package com.github.BamsGianYagami.POSSpringWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCheckoutDTO {
    int itemId;
    String itemName;
    Integer itemPrice;
    int qty;
    int totalPrice;
}
