package com.revshop.productservice.dto;

import java.util.List;

import com.revshop.productservice.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {
	
	private Long productId; 
	
	private String productName; 
	
	private String productDescription; 
	
	private double price; 
	
	private long Stock; 
	
	private String sizesAvailable; 
	
	private String colorsAvailable; 
	
	private List<String> productImages; 
	
	private String sleeve; 
	
	private String neckType; 
	
	private String Fabric; 
	
	private String pattern; 
	
	private Category category; 
	
	private String Brand; 
	

}
