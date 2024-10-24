package com.revshop.clientapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.revshop.clientapp.dto.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class CustomerController {
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
    
    @RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
    public ModelAndView registerCustomer(CustomerDto customer, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        List<ServiceInstance> instances = discoveryClient.getInstances("AUTHENTICATIONSERVICE");

        if (instances.isEmpty()) {
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Customer service is unavailable.");
            return modelAndView;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/customer/customerRegistration";
        System.out.println(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CustomerDto> entity = new HttpEntity<>(customer, headers);

        try {
            ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);
            String responseBody = result.getBody();
            System.out.println("Response Body: " + responseBody);

            if (responseBody != null && responseBody.contains("Registration successful")) {
                request.getSession().setAttribute("alertMessage", "Customer registered successfully");
                modelAndView.setViewName("redirect:/customer/authentication.jsp");
            } else {
                request.getSession().setAttribute("alertMessage", "Customer registration failed");
                modelAndView.setViewName("redirect:/customer/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("alertMessage", "An error occurred during registration");
            modelAndView.setViewName("redirect:/customer/error.jsp");
        }

        return modelAndView;
    }
    
    
    @RequestMapping("/loginCustomer")
    public ModelAndView loginCustomer(CustomerDto customer, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();

        List<ServiceInstance> instances = discoveryClient.getInstances("AUTHENTICATIONSERVICE");

        if (instances.isEmpty()) {
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Customer service is unavailable.");
            return modelAndView;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/customer/customerLogin";
        System.out.println(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CustomerDto> entity = new HttpEntity<>(customer, headers);

        try {
            ResponseEntity<CustomerDto> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, CustomerDto.class);
            CustomerDto loggedInCustomer = result.getBody();
            System.out.println("Logged In Customer: " + loggedInCustomer);


            if (loggedInCustomer != null) {
                request.getSession().setAttribute("customer", loggedInCustomer);
				session.setAttribute("customerId", loggedInCustomer.getCustomerId());
				session.setAttribute("userName", loggedInCustomer.getUserName());
				session.setAttribute("email", loggedInCustomer.getEmail());
				session.setAttribute("phoneNumber", loggedInCustomer.getPhoneNumber());
				session.setAttribute("password", loggedInCustomer.getPassword());
				session.setAttribute("address", loggedInCustomer.getAddress());
                modelAndView.setViewName("redirect:/customer/homepage.jsp");
            } else {
                request.getSession().setAttribute("alertMessage", "Invalid email or password");
                modelAndView.setViewName("redirect:/customer/login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("alertMessage", "An error occurred during login");
            modelAndView.setViewName("redirect:/customer/error.jsp");
        }

        return modelAndView;
    }
    
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); 
		}
		return "redirect:/customer/authentication.jsp"; 
	}
    
    
//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public ModelAndView fetchAllProducts(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        // Discovering the product service
//        List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCTSERVICE");
//
//        if (instances.isEmpty()) {
//            modelAndView.setViewName("errorPage");
//            modelAndView.addObject("message", "Product service is unavailable.");
//            return modelAndView;
//        }
//
//        ServiceInstance serviceInstance = instances.get(0);
//        String baseUrl = serviceInstance.getUri().toString() + "/products/AllProducts"; 
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        try {
//            ResponseEntity<ProductsDTO[]> response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, ProductsDTO[].class);
//            ProductsDTO[] products = response.getBody();
//
//            System.out.println("Response Status Code: " + response.getStatusCode());
//            System.out.println("Response Body: " + Arrays.toString(products)); // Debugging line
//
//            if (products != null && products.length > 0) {
//                modelAndView.addObject("products", Arrays.asList(products));
//                modelAndView.setViewName("customer/home"); 
//            } else {
//                modelAndView.setViewName("errorPage");
//                modelAndView.addObject("message", "No products found.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            modelAndView.setViewName("errorPage");
//            modelAndView.addObject("message", "An error occurred while fetching products.");
//        }
//
//        return modelAndView;
//    }
}
