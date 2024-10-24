package com.revshop.productservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revshop.productservice.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

}
