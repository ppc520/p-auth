package com.ppc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.ppc.mapper")
public class mainApplication {
	public static void main(String[] args) {
		SpringApplication.run(mainApplication.class, args);
	}

}
