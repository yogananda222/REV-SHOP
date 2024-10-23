package com.revshop.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.authenticationservice.entity.Admin;
import com.revshop.authenticationservice.service.AdminService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/admin")
@Transactional
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/adminLogin")
	public ResponseEntity<Admin> adminLogin(@RequestBody Admin admin){
		
		Admin loggedInAdmin = adminService.adminLogin(admin); 
		
		return new ResponseEntity<Admin>(loggedInAdmin,HttpStatus.OK);
		
	}

}
