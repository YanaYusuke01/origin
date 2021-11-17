package com.example.betelgeuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MappedTypes(User.class)
//@MapperScan("com.example.betelgeuse.auth")
public class BetelgeuseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetelgeuseApplication.class, args);
	}

}
