package com.ppc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.ppc.mapper")
public class SecurityFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityFrameworkApplication.class, args);
	}

}
