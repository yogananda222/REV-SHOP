package com.revshop.authenticationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revshop.authenticationservice.dao.AdminDao;
import com.revshop.authenticationservice.entity.Admin;
import com.revshop.authenticationservice.exception.InvalidCredentialsException;

@Service
public class AdminService implements AdminServiceInterface{
	
	@Autowired
	private AdminDao adminDao;

    @Override
    public Admin adminLogin(Admin admin) {
        Optional<Admin> adminExists = adminDao.findByEmail(admin.getEmail());

        if (adminExists.isPresent()) {
            Admin existingAdmin = adminExists.get();
            if (existingAdmin.getPassword().equals(admin.getPassword())) {
                return existingAdmin;
            } else {
                throw new InvalidCredentialsException("Invalid Credentails");
            }
        }
        
        throw new InvalidCredentialsException("No account found for email: " + admin.getEmail());
    }

}
