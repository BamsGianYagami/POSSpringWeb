package com.github.BamsGianYagami.POSSpringWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class cartDTO {
    int itemId;
    String itemName;
    Integer itemPrice;
    int qty;
    int totalPrice;
}
