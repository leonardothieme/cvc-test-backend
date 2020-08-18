package com.cvc.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CvcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvcBackendApplication.class, args);
	}

}
