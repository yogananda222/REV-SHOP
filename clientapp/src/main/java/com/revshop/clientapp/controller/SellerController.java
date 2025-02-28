package com.revshop.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.revshop.clientapp.dto.SellerDto;

import jakarta.servlet.http.HttpServletRequest;

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

@Controller
public class SellerController {
	
	@Autowired
	private DiscoveryClient discoveryClinet;
	
	private static final String UPLOAD_DIR = "C:/Users/dell/git/microservices-architecture/clientapp/src/main/webapp/Images";
	
	
    @RequestMapping(value = "/registerSeller", method = RequestMethod.POST)
    public ModelAndView registerSeller(SellerDto seller, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

          List<ServiceInstance> instances = discoveryClinet.getInstances("AUTHENTICATIONSERVICE");
        if (instances.isEmpty()) {
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Seller service is unavailable.");
            return modelAndView;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/seller/sellerRegister";
        System.out.println(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SellerDto> entity = new HttpEntity<>(seller, headers);

        try {
            ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);
            String responseBody = result.getBody();
            System.out.println("Response Body: " + responseBody);

            if (responseBody != null && responseBody.contains("Registration successful")) {
                request.getSession().setAttribute("alertMessage", "Seller registered successfully");
                modelAndView.setViewName("redirect:/seller/authentication.jsp");
            } else {
                request.getSession().setAttribute("alertMessage", "Seller registration failed");
                modelAndView.setViewName("redirect:/seller/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("alertMessage", "An error occurred during registration");
            modelAndView.setViewName("redirect:/seller/error.jsp");
        }

        return modelAndView;
    }

    @RequestMapping("/loginSeller")
    public ModelAndView loginSeller(SellerDto seller, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

		List<ServiceInstance> instances = discoveryClinet.getInstances("AUTHENTICATIONSERVICE");
        if (instances.isEmpty()) {
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Seller service is unavailable.");
            return modelAndView;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/seller/sellerLogin";
        System.out.println(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SellerDto> entity = new HttpEntity<>(seller, headers);

        try {
            ResponseEntity<SellerDto> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, SellerDto.class);
            SellerDto loggedInSeller = result.getBody();
            System.out.println("Logged In Seller: " + loggedInSeller);

            if (loggedInSeller != null) {
                request.getSession().setAttribute("seller", loggedInSeller);
                modelAndView.setViewName("redirect:/seller/home.jsp");
            } else {
                request.getSession().setAttribute("alertMessage", "Invalid email or password");
                modelAndView.setViewName("redirect:/seller/login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("alertMessage", "An error occurred during login");
            modelAndView.setViewName("redirect:/seller/error.jsp");
        }

        return modelAndView;
    }

}
