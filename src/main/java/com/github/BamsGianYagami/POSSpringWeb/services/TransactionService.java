package com.github.BamsGianYagami.POSSpringWeb.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.BamsGianYagami.POSSpringWeb.Entity.MasterTransaction;
import com.github.BamsGianYagami.POSSpringWeb.Entity.ShoppingCart;
import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.Entity.TransactionDetail;
import com.github.BamsGianYagami.POSSpringWeb.repository.MasterTransactionRepository;
import com.github.BamsGianYagami.POSSpringWeb.repository.ShoppingCartRepository;
import com.github.BamsGianYagami.POSSpringWeb.repository.StockRepository;
import com.github.BamsGianYagami.POSSpringWeb.repository.TransactionDetailRepository;

@Service
public class TransactionService {
    public static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    MasterTransactionRepository masterTransactionRepo;

    @Autowired
    StockRepository stockRepo;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    public Boolean SaveTransactionFromCart(List<ShoppingCartRepository.ListCart> carts, String username){

        //kumpulkan list barang dan hitung total keseluruhan
        Set<TransactionDetail> details = new HashSet<TransactionDetail>();
        float grandTotal = 0.f;
        for(var cart : carts){
            Integer itemId = cart.getItemId();
            Optional<Stock> optionalStock = stockRepo.findById(itemId);
            if(optionalStock.isPresent()){
                Stock stock = optionalStock.get();
                float total = cart.getQty() * stock.getItemPrice();
                TransactionDetail detail =
                new TransactionDetail(
                        itemId,
                        stock.getItemName(),
                        cart.getQty(),
                        stock.getItemPrice(),
                        total
                    );
                details.add(detail);
                grandTotal += total;
            } else
                return false; //jika ada data barang yang telah dihapus, kembalikan false
        }

        /**Save master transaction nya supaya dapat transaction id */
        MasterTransaction masterTransaction = new MasterTransaction(new Timestamp(Instant.now().toEpochMilli()), username);
        masterTransaction.setGrandTotal(grandTotal);
        Integer transactionId = masterTransactionRepo.save(masterTransaction).getId();

        //**masukan transaction Id di tiap transaction detail lalu save */
        details.stream().forEach(data -> data.setId(transactionId));
        transactionDetailRepository.saveAll(details);
        return true;
    }
}
