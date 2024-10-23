package com.revshop.productservice.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId; 
	
	private String productName; 
	
	private String productDescription; 
	
	private double price; 
	
	private long Stock; 
	
	private String sizesAvailable; 
	
	private String colorsAvailable; 
	
	@ElementCollection
	@CollectionTable(name="product_Images", joinColumns = @JoinColumn(name="product_id"))
	private List<String> productImages; 
	
	private String sleeve; 
	
	private String neckType; 
	
	private String Fabric; 
	
	private String pattern; 
	
	@Enumerated(EnumType.STRING)
	private Category category; 
	
	private String Brand; 
	
	
	

}
