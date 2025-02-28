package com.revshop.authenticationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.authenticationservice.dao.SellerDao;
import com.revshop.authenticationservice.entity.Seller;
import com.revshop.authenticationservice.exception.BlockedException;
import com.revshop.authenticationservice.exception.EmailExistsException;
import com.revshop.authenticationservice.exception.SellerNotApprovedException;

import jakarta.transaction.Transactional;

@Service
public class SellerService implements SellerServiceInterface {
	
	@Autowired
	private SellerDao sellerDao; 

	@Transactional
    @Override
    public int sellerRegisteration(Seller seller) {
    	
        if (emailAlreadyExists(seller)) {
            throw new EmailExistsException("Email already exists for this seller." + seller.getEmail());
        }
        

        sellerDao.save(seller);
        return 1; 
    }
    
    @Transactional
    @Override
    public Seller sellerLogin(Seller seller) {

        Seller existingSeller = sellerDao.findByEmail(seller.getEmail());

        if (existingSeller == null) {
            return null; 
        }

        if (existingSeller.isBlocked()) {
            throw new BlockedException("This seller account has been blocked.");
        } else if (!existingSeller.isApproved()) {
            throw new SellerNotApprovedException("Seller account is not approved yet.");
        }

        if (existingSeller.getPassword().equals(seller.getPassword())) {
            return existingSeller;
        } else {
            return null; 
        }
    }

	@Override
	public boolean emailAlreadyExists(Seller seller) {
		
		return sellerDao.existsByEmail(seller.getEmail());
	}

}
