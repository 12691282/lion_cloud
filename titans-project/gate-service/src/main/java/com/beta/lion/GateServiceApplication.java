package com.beta.lion;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableAutoConfiguration
@SpringCloudApplication
public class GateServiceApplication {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(GateServiceApplication.class).web(true).run(args);
	}

}
