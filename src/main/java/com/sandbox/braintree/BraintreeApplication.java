package com.sandbox.braintree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sandbox.braintree"})
public class BraintreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BraintreeApplication.class, args);
	}

}
