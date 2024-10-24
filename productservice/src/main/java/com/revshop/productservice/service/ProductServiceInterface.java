package com.revshop.productservice.service;

import java.util.List;

import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Products;


public interface ProductServiceInterface {
	
    Products addProduct(Products product, Long sellerId);

	List<Products> getProductsBySellerId(Long sellerId);

	List<Products> getAllProducts();

//	Products addProduct(Products product, Long sellerId, MultipartFile[] productImages);

	Products getProductById(Long productId);
	
	Products updateProduct(Long productId, Products updatedProduct);
	
	void deleteProduct(Long productId);
	
	long productsCount();

	List<Products> getProductsByCategory(Category category);

	List<Products> getProductsSortedByPriceAsc();

	List<Products> getProductsSortedByPriceDesc();



}
