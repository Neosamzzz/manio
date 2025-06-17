package com.proj.manio;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.proj.manio.mapper")
@SpringBootApplication
public class ManioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManioApplication.class, args);
	}

}
