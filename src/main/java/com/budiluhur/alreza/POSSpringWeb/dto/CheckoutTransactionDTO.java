package com.budiluhur.alreza.POSSpringWeb.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutTransactionDTO {
    Integer GrandTotal;
    List<cartDTO> listItem;
}
