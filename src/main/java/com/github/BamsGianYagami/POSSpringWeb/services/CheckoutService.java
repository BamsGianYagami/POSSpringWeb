package com.github.BamsGianYagami.POSSpringWeb.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCart;
import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCartId;
import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.dto.ItemCheckoutDTO;
import com.github.BamsGianYagami.POSSpringWeb.repository.ShoppingCartRepository;

@Service
public class CheckoutService {
    public static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    StockService stockService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    public List<ItemCheckoutDTO> getStocksInformation(List<ItemCheckoutDTO> listItem){
        for(ItemCheckoutDTO item : listItem){
            Stock s = stockService.getStock(item.getItemId());
            item.setItemName(s.getItemName());
            item.setItemPrice(s.getItemPrice());
            item.setTotalPrice(s.getItemPrice() * item.getQty());
        }
        return listItem;
    }

    public List<ItemCheckoutDTO> addToCart(int userId, int itemId){
        ShoppingCartId shoppingCardID = new ShoppingCartId(userId, itemId);
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCardID);
        ShoppingCart shoppingCartEntity;
        if(shoppingCart.isPresent()){
            shoppingCartEntity =  shoppingCart.get();
            int qty = shoppingCartEntity.getQty();
            shoppingCartEntity.setQty(qty++);
            shoppingCartRepository.save(shoppingCartEntity);
        } else{
            shoppingCartEntity = new ShoppingCart(userId, itemId, 1);
            shoppingCartRepository.save(shoppingCartEntity);
        }

        return null;
    }

    public boolean saveTransaction(List<ItemCheckoutDTO> listItem){
        return true;
    }
}
