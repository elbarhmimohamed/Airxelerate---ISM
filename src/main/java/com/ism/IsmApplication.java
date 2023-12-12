package com.ism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class IsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsmApplication.class, args);
	}

}
