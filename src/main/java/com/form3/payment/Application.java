package com.form3.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static final String VERSION = "/api/payment/v1" ;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
