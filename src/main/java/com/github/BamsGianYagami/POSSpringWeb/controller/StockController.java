package com.github.BamsGianYagami.POSSpringWeb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="stock")
public class StockController {
    public static Logger log = LoggerFactory.getLogger(StockController.class);

    @GetMapping
    public Object getStock(){
        return new Object();
    }

    @PostMapping
    public ResponseEntity<?> addStock(){
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateStock(){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteStock(){
        return ResponseEntity.ok().build();
    }
}
