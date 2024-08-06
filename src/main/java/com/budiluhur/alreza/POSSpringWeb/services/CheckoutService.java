package com.budiluhur.alreza.POSSpringWeb.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budiluhur.alreza.POSSpringWeb.Entity.ShoppingCart;
import com.budiluhur.alreza.POSSpringWeb.Entity.ShoppingCartId;
import com.budiluhur.alreza.POSSpringWeb.Entity.Stock;
import com.budiluhur.alreza.POSSpringWeb.dto.cartDTO;
import com.budiluhur.alreza.POSSpringWeb.repository.ShoppingCartRepository;

@Service
public class CheckoutService {
    public static final Logger log = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    StockService stockService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    public List<cartDTO> getStocksInformation(List<cartDTO> listItem){
        for(cartDTO item : listItem){
            Stock s = stockService.getStock(item.getItemId());
            item.setItemName(s.getItemName());
            item.setItemPrice(s.getItemPrice());
            item.setTotalPrice(s.getItemPrice() * item.getQty());
        }
        return listItem;
    }

    public void addToCart(String username, int itemId, float qty){
        ShoppingCartId shoppingCardID = new ShoppingCartId(username, itemId);
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCardID);
        ShoppingCart shoppingCartEntity;
        if(shoppingCart.isPresent()){
            shoppingCartEntity =  shoppingCart.get();
            shoppingCartEntity.setQty(qty);
            shoppingCartRepository.save(shoppingCartEntity);
        } else{
            shoppingCartEntity = new ShoppingCart(username, itemId, qty);
            shoppingCartRepository.save(shoppingCartEntity);
        }
    }

    public void removeFromCart(String username, int itemId, float qty){
        ShoppingCartId shoppingCardID = new ShoppingCartId(username, itemId);
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(shoppingCardID);
        ShoppingCart shoppingCartEntity;
        if(shoppingCart.isPresent()){
            shoppingCartEntity =  shoppingCart.get();
            if(qty==shoppingCartEntity.getQty()) {
                shoppingCartRepository.delete(shoppingCartEntity);
                log.info("deleted!");
            } else {
                shoppingCartEntity.reduceQty(qty);
                shoppingCartRepository.save(shoppingCartEntity);
                log.info("updated!");
            }
        }
    }

    public void removeAllFromCart(String username){
        shoppingCartRepository.removeAllFromCart(username);
    }

    public List<ShoppingCartRepository.ListCart> getListCarts(String username){
        
        List<ShoppingCartRepository.ListCart> listCheckout = shoppingCartRepository.getListCartByUsername(username);
        return listCheckout;
    }

    public Integer calculateGrandTotal(List<ShoppingCartRepository.ListCart> carts){
        Integer total = 0;
        for(ShoppingCartRepository.ListCart cart : carts){
            total += cart.getItemPrice() * cart.getQty();
        }
        return total;
    }

    public boolean saveTransaction(List<cartDTO> listItem){
        return true;
    }
}
