package com.again.api.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.again.api.auth.**.mapper")
public class AuthStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthStartApplication.class, args);
	}

}
