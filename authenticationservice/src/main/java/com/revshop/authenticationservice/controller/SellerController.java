package com.revshop.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.authenticationservice.entity.Seller;
import com.revshop.authenticationservice.service.SellerService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@Transactional
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/sellerRegister")
    public ResponseEntity<String> sellerRegisteration(@RequestBody Seller seller) {

    	 
        int result = sellerService.sellerRegisteration(seller);

        if (result == 1) {
            return new ResponseEntity<>("Seller registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Seller registration failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sellerLogin")
    public ResponseEntity<Seller> sellerLogin(@RequestBody Seller seller) {
        Seller loggedInSeller = sellerService.sellerLogin(seller);
        return new ResponseEntity<>(loggedInSeller, HttpStatus.OK);
    }
}
