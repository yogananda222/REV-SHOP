package com.revshop.clientapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	
	private Long customerId; 
	
	private String userName; 
	
	private String password; 
	
	private String email; 
	
	private String phoneNumber;
	
	private String Address; 
	
	private String pincode;


}
