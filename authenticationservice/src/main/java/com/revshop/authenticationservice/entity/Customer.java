package com.revshop.authenticationservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId; 
	
	@NotBlank(message = "Username is required")
	@Size(min = 4, message = "Username must be at least 4 characters long")
	@Column(nullable = false)
	private String userName; 
	
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$", 
			message = "Password must have at least one uppercase, one lowercase, one number, and one special character")
	@Column(nullable = false)
	private String password; 
	
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column(unique=true, nullable = false)
	private String email; 
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
	@Column(nullable = false)
	private String phoneNumber;
	
	@NotBlank(message = "Address is required")
	@Column(nullable = false)
	private String address; 
	
	@NotBlank(message = "Pincode is required")
	@Pattern(regexp = "^\\d{6}$", message = "Pincode must be 6 digits")
	@Column(nullable = false)
	private String pincode;

}
