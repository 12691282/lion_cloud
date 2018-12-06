package com.lion.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class WebApplication {

  
	public static void main(String[] args) {
//		SpringApplication.run(WebApplication.class, args);
//      String url = "http://127.0.0.1:9999/auth/oauth/token";
      String url = "http://192.168.2.103:53620/bus/refresh";
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
      headers.setContentType(type);
      headers.add("Accept", MediaType.APPLICATION_JSON.toString());

      HttpEntity<String> formEntity = new HttpEntity<String>("", headers);
      String result = restTemplate.postForObject(url, formEntity, String.class);

//      SpringApplication.run(PigSsoClientDemoApplication.class, args);
     System.out.println( result);
	
	}
	
}
