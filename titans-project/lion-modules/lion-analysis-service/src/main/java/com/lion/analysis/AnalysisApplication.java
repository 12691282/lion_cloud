package com.lion.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class AnalysisApplication {

  
	public static void main(String[] args) {
		SpringApplication.run(AnalysisApplication.class, args);
	}
	
}
