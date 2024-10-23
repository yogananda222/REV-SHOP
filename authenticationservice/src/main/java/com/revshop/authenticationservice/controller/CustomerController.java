package com.revshop.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.authenticationservice.entity.Customer;
import com.revshop.authenticationservice.service.CustomerService;

import jakarta.transaction.Transactional;

@RestController
@Transactional
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customerRegistration")
	public ResponseEntity<String> customerRegistration(@RequestBody Customer customer){
		
		int result = customerService.customerRegistration(customer);
		
		if(result==1) {
			return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Registration failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/customerLogin")
	public ResponseEntity<Customer> customerLogin(@RequestBody Customer customer){
		
		Customer loggedInCustomer = customerService.customerLogin(customer); 
		
		return new ResponseEntity<Customer>(loggedInCustomer,HttpStatus.OK);
	}
	

}
