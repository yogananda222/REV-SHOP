package com.revshop.authenticationservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.authenticationservice.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long>{

	boolean existsByEmail(String email);

	Optional<Customer> findByEmail(String email);

}
