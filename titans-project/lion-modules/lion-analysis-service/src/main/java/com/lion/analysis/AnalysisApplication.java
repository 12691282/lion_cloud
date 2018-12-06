package com.lion.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@SpringBootApplication
public class AnalysisApplication {

  
	public static void main(String[] args) {
      String url = "http://127.0.0.1:9999/auth/oauth/token";
//      String url = "http://192.168.1.114:58166/bus/refresh";
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();

      HttpEntity<String> formEntity = new HttpEntity<String>("", headers);
      String result = restTemplate.postForObject(url, formEntity, String.class);

//      SpringApplication.run(PigSsoClientDemoApplication.class, args);
     System.out.println( result);
		
//		SpringApplication.run(AnalysisApplication.class, args);
		
	}
	
}
