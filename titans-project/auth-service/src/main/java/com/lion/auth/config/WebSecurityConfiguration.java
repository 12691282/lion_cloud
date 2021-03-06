package com.lion.auth.config;

import com.lion.common.general.config.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER - 1)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private FilterIgnorePropertiesConfig filterIgnoreConfig;
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	//登录页面
    	ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
    	http.formLogin().loginPage("/authentication/require")
        //登录页面提交数据连接
        .loginProcessingUrl("/authentication/form")
        .and()
        .authorizeRequests();
    	filterIgnoreConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
    	registry.anyRequest().authenticated()
        .and()
        .csrf().disable();
    	
    }

}
