package com.criscahub.erp_lite;

import com.criscahub.erp_lite.persistence.rest.adapters.JsonPlaceholderCustomerProviderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpLiteApplication implements CommandLineRunner{

	@Autowired
	JsonPlaceholderCustomerProviderAdapter jsonPlaceholderCustomerProviderAdapter;

	public static void main(String[] args) {
		SpringApplication.run(ErpLiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var r = jsonPlaceholderCustomerProviderAdapter.findById(3L);
		System.out.println(r.get().name());
	}
}
