package com.budiluhur.alreza.POSSpringWeb.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.budiluhur.alreza.POSSpringWeb.Entity.MasterTransaction;
import com.budiluhur.alreza.POSSpringWeb.Entity.Stock;
import com.budiluhur.alreza.POSSpringWeb.Entity.TransactionDetail;
import com.budiluhur.alreza.POSSpringWeb.repository.MasterTransactionRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.ShoppingCartRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.StockRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.TransactionDetailRepository;
import com.budiluhur.alreza.POSSpringWeb.repository.TransactionDetailRepository.listTransactionDetail;

@Service
public class TransactionService {
    public static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    MasterTransactionRepository masterTransactionRepo;

    @Autowired
    StockRepository stockRepo;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    public Integer SaveTransactionFromCart(List<ShoppingCartRepository.ListCart> carts, String username){

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
                        cart.getQty(),
                        total
                    );
                details.add(detail);
                grandTotal += total;
            } else
                return null; //jika ada data barang yang telah dihapus, kembalikan null
        }

        /**Save master transaction nya supaya dapat transaction id */
        MasterTransaction masterTransaction = new MasterTransaction(new Timestamp(Instant.now().toEpochMilli()), username);
        masterTransaction.setGrandTotal(grandTotal);
        Integer transactionId = masterTransactionRepo.save(masterTransaction).getId();

        //**masukan transaction Id di tiap transaction detail lalu save */
        details.stream().forEach(data -> data.setId(transactionId));
        transactionDetailRepository.saveAll(details);
        return transactionId;
    }

    public List<MasterTransaction> getTransactionSummary(){
        Sort sortBy = Sort.by(Direction.DESC, "transactionTime");
        return masterTransactionRepo.findAll(sortBy);
    }

    public MasterTransaction getTransaction(Integer id){
        return masterTransactionRepo.findById(id).get();
    }

    public List<listTransactionDetail> getTransactionDetail(Integer id){
        return transactionDetailRepository.getGransactionDetailById(id);
    }

    public float calculateGrandTotal(List<listTransactionDetail> transactionDetail) {
        float grandTotal = 0;
        for(listTransactionDetail detail : transactionDetail){
            grandTotal += detail.getTotalPrice();
        }
        return grandTotal;
    }
}
