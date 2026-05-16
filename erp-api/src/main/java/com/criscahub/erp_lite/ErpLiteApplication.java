package com.criscahub.erp_lite;

import com.criscahub.erp_lite.queries.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class ErpLiteApplication implements CommandLineRunner{

//	@Autowired
//	private PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(ErpLiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("ADMIN: +" + this.encoder.encode("admin"));
//		System.out.println("MANAGER: +" + this.encoder.encode("manager"));
//		System.out.println("EMPLOYEE: +" + this.encoder.encode("employee"));

	}



}
