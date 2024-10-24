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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.revshop.clientapp.dto.AdminDto;
import com.revshop.clientapp.dto.SellerDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
    
    @RequestMapping("/loginAdmin")
    public ModelAndView loginAdmin(AdminDto admin, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        
        List<ServiceInstance> instances = discoveryClient.getInstances("AUTHENTICATIONSERVICE");
        
        if (instances.isEmpty()) {
            modelAndView.setViewName("errorPage");
            modelAndView.addObject("message", "Admin service is unavailable.");
            return modelAndView;
        }

        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = serviceInstance.getUri().toString() + "/admin/adminLogin";
        System.out.println(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<AdminDto> entity = new HttpEntity<>(admin, headers);

        try {
            ResponseEntity<AdminDto> result = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, AdminDto.class);
            AdminDto loggedInAdmin = result.getBody();
            System.out.println("Logged In Seller: " + loggedInAdmin);

            if (loggedInAdmin != null) {
                request.getSession().setAttribute("seller", loggedInAdmin);
                modelAndView.setViewName("redirect:/admin/home.jsp");
            } else {
                request.getSession().setAttribute("alertMessage", "Invalid email or password");
                modelAndView.setViewName("redirect:/admin/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("alertMessage", "An error occurred during login");
            modelAndView.setViewName("redirect:/admin/error.jsp");
        }

        return modelAndView;
    }

}
