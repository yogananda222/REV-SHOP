package com.revshop.authenticationservice.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.authenticationservice.entity.Admin;
import com.revshop.authenticationservice.entity.Customer;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmail(String email);

}
