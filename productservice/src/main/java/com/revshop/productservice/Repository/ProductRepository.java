package com.revshop.productservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	
	List<Products> findBySeller_SellerId(Long sellerId);
	
	 List<Products> findAll();

	List<Products> findByCategory(Category category);
	
	  List<Products> findAllByOrderByPriceAsc();
	
	List<Products> findAllByOrderByPriceDesc(); 
	

}
