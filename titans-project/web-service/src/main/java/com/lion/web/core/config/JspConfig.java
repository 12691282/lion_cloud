package com.lion.web.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class JspConfig extends WebMvcConfigurerAdapter {
	
	@Bean
    public InternalResourceViewResolver internalResourceViewResolver () {
		 InternalResourceViewResolver viewResolver = new   InternalResourceViewResolver();
         viewResolver.setPrefix("/WEB-INF/page/");
         viewResolver.setSuffix(".jsp");
        return viewResolver;
	}

	 @Override
     public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
         configurer.enable();
     }
}
