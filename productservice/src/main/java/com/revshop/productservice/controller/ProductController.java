package com.revshop.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revshop.productservice.entity.Category;
import com.revshop.productservice.entity.Products;
import com.revshop.productservice.exception.ProductNotFoundException;
import com.revshop.productservice.service.ProductService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:8082/")
@Transactional
public class ProductController {
	
    @Autowired
    private ProductService productService;

    @PostMapping("/seller/{sellerId}/add")
    public ResponseEntity<Products> addProduct(@RequestBody Products product, @PathVariable Long sellerId) {
        Products addedProduct = productService.addProduct(product, sellerId);
        return ResponseEntity.ok(addedProduct);
    }
    
//    @PostMapping("/{sellerId}/add")
//    public ResponseEntity<Products> addProducts(
//            @RequestParam("productName") String productName,
//            @RequestParam("productDescription") String productDescription,
//            @RequestParam("price") double price,
//            @RequestParam("stock") int stock,
//            @RequestParam("sizesAvailable") String sizesAvailable,
//            @RequestParam("colorsAvailable") String colorsAvailable,
//            @RequestParam("sleeve") String sleeve,
//            @RequestParam("neckType") String neckType,
//            @RequestParam("fabric") String fabric,
//            @RequestParam("pattern") String pattern,
//            @RequestParam("category") Category category,
//            @RequestParam("brand") String brand,
//            @RequestParam("productImages") MultipartFile[] productImages,
//            @PathVariable Long sellerId
//    ) {
//        Products product = new Products();
//        product.setProductName(productName);
//        product.setProductDescription(productDescription);
//        product.setPrice(price);
//        product.setStock(stock);
//        product.setSizesAvailable(sizesAvailable);
//        product.setColorsAvailable(colorsAvailable);
//        product.setSleeve(sleeve);
//        product.setNeckType(neckType);
//        product.setFabric(fabric);
//        product.setPattern(pattern);
//        product.setCategory(category);
//        product.setBrand(brand);
//        
//        
//        Products addedProduct = productService.addProduct(product, sellerId, productImages);
//        return ResponseEntity.ok(addedProduct);
//    }
    
    @GetMapping("/seller/{sellerId}")
    public List<Products> getProductsBySellerId(@PathVariable Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }
    
    @GetMapping("/AllProducts")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable Long productId) {
        Products product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    


    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") Long productId, @RequestBody Products updatedProduct) {
        try {
            Products product = productService.updateProduct(productId, updatedProduct);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } catch (ProductNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/productsCount")
    public ResponseEntity<Long> getTotalProductCount() {
        try {
            long count = productService.productsCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable String category) {
        try {
            // Clean up the category input
            category = category.trim().replace(",", "");
            
            // Convert the cleaned string to a Category enum
            Category categoryEnum = Category.valueOf(category.toUpperCase());

            List<Products> products = productService.getProductsByCategory(categoryEnum);
            return ResponseEntity.ok(products);

        } catch (IllegalArgumentException e) {
            // If category string doesn't match the enum
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    
    @GetMapping("/sort/low-to-high")
    public List<Products> getProductsLowToHigh() {
        return productService.getProductsSortedByPriceAsc();
    }

    @GetMapping("/sort/high-to-low")
    public List<Products> getProductsHighToLow() {
        return productService.getProductsSortedByPriceDesc();
    }
    


}