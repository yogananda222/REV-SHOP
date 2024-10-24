package com.revshop.productservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.revshop.productservice.Repository.ProductRepository;
import com.revshop.productservice.Repository.SellerRepository;
import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Products;
import com.revshop.productservice.entity.Seller;
import com.revshop.productservice.exception.ProductNotFoundException;

@Service
public class ProductService implements ProductServiceInterface {
	
	
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Products addProduct(Products product, Long sellerId) {
        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
        if (sellerOpt.isPresent()) {
            Seller seller = sellerOpt.get();
            product.setSeller(seller); 
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Seller not found with id: " + sellerId);
        }
    }
    
//    @Override
//    public Products addProduct(Products product, Long sellerId, MultipartFile[] productImages) {
//        Optional<Seller> sellerOpt = sellerRepository.findById(sellerId);
//        if (sellerOpt.isPresent()) {
//            Seller seller = sellerOpt.get();
//            product.setSeller(seller); 
//
//            List<String> savedImagePaths = new ArrayList<>();
//            if (productImages != null && productImages.length > 0) {
//                for (MultipartFile image : productImages) {
//                    try {
//                        String savedImagePath = FileUpload.saveFile(image);
//                        savedImagePaths.add(savedImagePath); 
//                    } catch (IOException e) {
//                        throw new RuntimeException("Failed to store image: " + image.getOriginalFilename(), e);
//                    }
//                }
//            }
//            product.setProductImages(savedImagePaths); 
//            return productRepository.save(product);
//        } else {
//            throw new RuntimeException("Seller not found with id: " + sellerId);
//            
//        }
//        
//      }
    
    @Override
    public List<Products> getProductsBySellerId(Long sellerId) {
        return productRepository.findBySeller_SellerId(sellerId);
    }
    
    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll(); 
    }
    
    @Override
    public Products getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);  
    }
    
 
    
    @Override
    public Products updateProduct(Long productId, Products updatedProduct) {

        Optional<Products> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Products existingProduct = productOpt.get();
            
   
            if (updatedProduct.getProductName() != null) {
                existingProduct.setProductName(updatedProduct.getProductName());
            }
            if (updatedProduct.getPrice() != 0.0) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getSizesAvailable() != null) {
                existingProduct.setSizesAvailable(updatedProduct.getSizesAvailable());
            }
            if (updatedProduct.getColorsAvailable() != null) {
                existingProduct.setSizesAvailable(updatedProduct.getSizesAvailable());
            }
            if (updatedProduct.getProductDescription() != null) {
                existingProduct.setProductDescription(updatedProduct.getProductDescription());
            }
            if (updatedProduct.getProductImages() != null) {
                existingProduct.setProductImages(updatedProduct.getProductImages());
            }
            if (updatedProduct.getStock() != 0.0) {
                existingProduct.setStock(updatedProduct.getStock());
            }

            return productRepository.save(existingProduct);
        } else {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
    }

    
    @Override
    public void deleteProduct(Long productId) {
      
        Optional<Products> productOpt = productRepository.findById(productId);
        
        if (productOpt.isPresent()) {
            productRepository.delete(productOpt.get());
        } else {
            throw new ProductNotFoundException("Product not found with id: " + productId);
        }
    }
    
    @Override
    public long productsCount() {
        return productRepository.count();
    }

    @Override
    public List<Products> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Products> getProductsSortedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Products> getProductsSortedByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

}