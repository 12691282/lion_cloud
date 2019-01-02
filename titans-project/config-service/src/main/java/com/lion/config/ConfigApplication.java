package com.lion.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
@Slf4j
public class ConfigApplication {

	public static void main(String[] args) {
		log.info("config service is start == >");
		SpringApplication.run(ConfigApplication.class, args);
		log.info("config service is ready < ==");
	}
	
}
