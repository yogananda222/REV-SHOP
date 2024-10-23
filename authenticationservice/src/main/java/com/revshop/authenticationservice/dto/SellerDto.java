package com.revshop.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {
	
	 private Long sellerId; 
	 
	  private String username; 
	  
	  private String password; 
	  
	  private String email;
	  
	  private String businessName; 
	  
	  private String GSTIN; 
	  
	  private String phoneNumber; 
	  
	  private String address; 
	  
	  private String pincode;
	  
	  private boolean blocked; 
	    
	  private boolean approved; 

}
