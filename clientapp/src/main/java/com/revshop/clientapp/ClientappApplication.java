package com.revshop.clientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientappApplication.class, args);
	}

}
