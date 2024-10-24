package com.revshop.clientapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.revshop.clientapp.dto.ProductsDTO;

@Controller
public class ProductController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/getAllProducts")
    public ModelAndView getAllProducts(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customer/homepage.jsp");

        List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCTSERVICE");

        if (instances.isEmpty()) {
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Product service is unavailable.");
            return modelAndView;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/products/AllProducts";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<ProductsDTO[]> result = restTemplate.getForEntity(baseUrl, ProductsDTO[].class);
            ProductsDTO[] productsArray = result.getBody();

            if (productsArray != null) {
                List<ProductsDTO> productsList = Arrays.asList(productsArray);
                
                // Store products in session
                HttpSession session = request.getSession();
                session.setAttribute("productsList", productsList);
            } else {
                modelAndView.addObject("message", "No products found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Error fetching products.");
        }

        return modelAndView;
    }
}
