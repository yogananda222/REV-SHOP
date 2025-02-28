package com.revshop.authenticationservice.service;

import com.revshop.authenticationservice.entity.Seller;

public interface SellerServiceInterface {
	
	
	int sellerRegisteration(Seller seller); 
	
	Seller sellerLogin(Seller seller); 

	boolean emailAlreadyExists(Seller seller); 
}
