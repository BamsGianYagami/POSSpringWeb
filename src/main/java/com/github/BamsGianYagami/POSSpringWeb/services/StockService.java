package com.github.BamsGianYagami.POSSpringWeb.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.BamsGianYagami.POSSpringWeb.Entity.Stock;
import com.github.BamsGianYagami.POSSpringWeb.repository.StockRepository;

@Service
public class StockService {
    public static final Logger log = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository repository;

    public List<Stock> getAllStock(){
        return repository.findAll();
    }

    public Stock getStock(Integer id){
        try{
            return repository.findById(id).get();
        }catch(NoSuchElementException e){
            return new Stock();
        }
    }

    public Stock addStock(Stock newStock){
        repository.save(newStock);
        return newStock;
    }

    public Stock updateStock(Stock newData){
        Integer id = newData.getItemId();
        Optional<Stock> optional= repository.findById(id);
        if(optional.isPresent()){
            Stock existing = optional.get();
            existing.setItemName(newData.getItemName());
            existing.setItemPrice(newData.getItemPrice());
            existing.setQty(newData.getQty());
            existing.setUnitCount(newData.getUnitCount());
            repository.save(existing);
            return existing;
        } else
            return null;
    }

    public String deleteStock(Integer id){
        repository.deleteById(id);
        return "Stock id: "+id+" has been deleted!";
    }
}
