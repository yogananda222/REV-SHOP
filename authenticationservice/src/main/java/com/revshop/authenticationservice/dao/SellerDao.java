package com.revshop.authenticationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.authenticationservice.entity.Seller;

@Repository
public interface SellerDao extends JpaRepository<Seller, Long> {

	boolean existsByEmail(String email);

	Seller findByEmail(String email); 
		

}
