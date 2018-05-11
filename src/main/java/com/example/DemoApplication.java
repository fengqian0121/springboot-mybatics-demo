package com.example;

import com.example.util.ParseXMLUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		ParseXMLUtil.planList();
		SpringApplication.run(DemoApplication.class, args);
	}
}
