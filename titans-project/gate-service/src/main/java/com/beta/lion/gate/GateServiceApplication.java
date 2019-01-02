package com.beta.lion.gate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy
@EnableAutoConfiguration
@SpringCloudApplication
@ComponentScan(basePackages = {"com.beta.lion.gate", "com.lion.common.general"})
@Slf4j
public class GateServiceApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GateServiceApplication.class).web(true).run(args);
		log.info("Gate Way service is start");
	}

}
