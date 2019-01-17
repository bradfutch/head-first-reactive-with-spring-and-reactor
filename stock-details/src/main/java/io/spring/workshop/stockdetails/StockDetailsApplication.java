package io.spring.workshop.stockdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class StockDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDetailsApplication.class, args);
	}

}

