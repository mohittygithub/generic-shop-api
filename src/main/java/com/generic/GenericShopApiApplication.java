package com.generic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenericShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenericShopApiApplication.class, args);
		System.out.println("Server Connected!!!");
	}

}
