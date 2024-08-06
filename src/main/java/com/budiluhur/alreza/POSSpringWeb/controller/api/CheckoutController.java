package com.budiluhur.alreza.POSSpringWeb.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budiluhur.alreza.POSSpringWeb.dto.CheckoutTransactionDTO;
import com.budiluhur.alreza.POSSpringWeb.dto.cartDTO;
import com.budiluhur.alreza.POSSpringWeb.services.CheckoutService;

@RestController
@RequestMapping(path="api/checkout")
public class CheckoutController {
    public static Logger log = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    CheckoutService service;

    @GetMapping("showDetailCheckout")
    //untuk menampilkan informasi detail data yang dipilih saat checkout berikut total harga dari jumlah barang
    public ResponseEntity<?> showDetailCheckout(@RequestBody List<cartDTO> listItem){

        listItem = service.getStocksInformation(listItem);
        return ResponseEntity.ok(listItem);
    }

    @PostMapping("checkoutTransaction")
    public ResponseEntity<?> checkoutTransaction(@RequestBody List<cartDTO> listItem){
        CheckoutTransactionDTO response = new CheckoutTransactionDTO();

        listItem = service.getStocksInformation(listItem);
        boolean isSaved = service.saveTransaction(listItem);

        if(isSaved)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.status(500).body("failed to save transaction!");
    }

    
}
