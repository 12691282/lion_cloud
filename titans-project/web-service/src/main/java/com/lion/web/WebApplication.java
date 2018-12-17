package com.lion.web;

import com.alibaba.fastjson.JSON;
import com.lion.common.tools.Base64Tool;
import org.apache.http.message.BasicHeader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class WebApplication {


	public static void main(String[] args) throws UnsupportedEncodingException {
//		SpringApplication.run(WebApplication.class, args);

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36");
        headers.add("Accept-Encoding", "gzip,deflate");
        headers.add("Accept-Language", "zh-CN");
        headers.add("Connection", "Keep-Alive");
        String code = "lion:lion";
        String auth = "Basic "+Base64Tool.encode(code);
        headers.add("Authorization", auth);
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap();
        paramMap.add("username",  "admin");
        paramMap.add("password",  "lBTqrKS0kZixOFXeZ0HRng==");
        paramMap.add("randomStr", "19201544884633838");
        paramMap.add("code","1111");
        paramMap.add("grant_type","password");
        paramMap.add("scope","server");

//      String url = "http://127.0.0.1:49732/bus/refresh";
        String url = "http://127.0.0.1:9999/auth/oauth/token";

        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity(paramMap,headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(url, httpEntity, String.class);
//      SpringApplication.run(PigSsoClientDemoApplication.class, args);
        System.out.println( result);
	
	}
	
}
