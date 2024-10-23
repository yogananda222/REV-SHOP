package com.revshop.productservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.productservice.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

}
