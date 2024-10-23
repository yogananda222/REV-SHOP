package com.revshop.authenticationservice.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.authenticationservice.dao.CustomerDao;
import com.revshop.authenticationservice.entity.Customer;
import com.revshop.authenticationservice.exception.*;



@Service
public class CustomerService implements CustomerServiceInterface{
	
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public int customerRegistration(Customer customer) {
		
		if(customerDao.existsByEmail(customer.getEmail())){
			
			throw new EmailExistsException("Email Already Exists: " + customer.getEmail());
		}
		customerDao.save(customer);
		return 1;
	}

    @Override
    public Customer customerLogin(Customer customer) {
        Optional<Customer> customerExists = customerDao.findByEmail(customer.getEmail());

        if (customerExists.isPresent()) {
            Customer existingCustomer = customerExists.get();
            if (existingCustomer.getPassword().equals(customer.getPassword())) {
                return existingCustomer;
            } else {
                throw new InvalidCredentialsException("Invalid Credentails");
            }
        }
        
        throw new InvalidCredentialsException("No account found for email: " + customer.getEmail());
    }


	@Override
	public boolean emailAlreadyExists(Customer customer) {
	
		return customerDao.existsByEmail(customer.getEmail());
	}
	
	
	
	
	

}
