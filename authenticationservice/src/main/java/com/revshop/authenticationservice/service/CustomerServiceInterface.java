package com.revshop.authenticationservice.service;

import com.revshop.authenticationservice.entity.Customer;

public interface CustomerServiceInterface {
	
	
	int customerRegistration(Customer customer);
	
	Customer customerLogin(Customer customer);
	
	boolean emailAlreadyExists(Customer customer);

}
