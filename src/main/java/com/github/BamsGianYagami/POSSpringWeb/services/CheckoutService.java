package com.github.BamsGianYagami.POSSpringWeb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.dto.ItemCheckoutDTO;

@Service
public class CheckoutService {
    public static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    StockService stockService;


    public List<ItemCheckoutDTO> getStocksInformation(List<ItemCheckoutDTO> listItem){
        for(ItemCheckoutDTO item : listItem){
            Stock s = stockService.getStock(item.getItemId());
            item.setItemName(s.getItemName());
            item.setItemPrice(s.getItemPrice());
            item.setTotalPrice(s.getItemPrice() * item.getQty());
        }
        return listItem;
    }

    public boolean saveTransaction(List<ItemCheckoutDTO> listItem){
        return true;
    }
}
