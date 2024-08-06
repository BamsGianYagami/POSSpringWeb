package com.budiluhur.alreza.POSSpringWeb.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budiluhur.alreza.POSSpringWeb.Entity.Stock;
import com.budiluhur.alreza.POSSpringWeb.services.StockService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping(path="api/stock")
public class StockController {
    public static Logger log = LoggerFactory.getLogger(StockController.class);

    @Autowired
    StockService service;

    @GetMapping()
    public ResponseEntity<?> getAllStock(){
        Object data = service.getAllStock();
        return ResponseEntity.ok(data);
    }

    @GetMapping("{id}")
    @PermitAll
    public ResponseEntity<?> getStock(@PathVariable Integer id){
        Object data = service.getStock(id);
        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<?> addStock(@RequestBody Stock stock){
        Stock saved = service.addStock(stock);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<?> updateStock(@RequestBody Stock stock){
        Stock updated = service.updateStock(stock);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStock(@PathVariable Integer id){
        String info = service.deleteStock(id);
        return ResponseEntity.ok(info);
    }
}
