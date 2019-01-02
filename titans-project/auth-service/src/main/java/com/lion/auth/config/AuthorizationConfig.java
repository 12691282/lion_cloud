package com.lion.auth.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.lion.auth.util.UserDetailsImpl;


/**
 * 
 * document to here http://www.cnblogs.com/charlypage/p/9383420.html
 * 
 * @author lion
 *
 */

@Configuration
@Order(Integer.MIN_VALUE)
@EnableAuthorizationServer
@Slf4j
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
    @Autowired
    private DataSource dataSource;
    
    
    //将ClientDetailsServiceConfigurer（从您的回调AuthorizationServerConfigurer）可以用来在内存或JDBC实现客户的细节服务来定义的。客户端的重要属性是
    //clientId：（必填）客户端ID。
    //secret:(可信客户端需要）客户机密码（如果有）。没有可不填
    //scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。read write all
    //authorizedGrantTypes：授予客户端使用授权的类型。默认值为空。
    //authorities授予客户的授权机构（普通的Spring Security权威机构）。
    //客户端的详细信息可以通过直接访问底层商店（例如，在数据库表中JdbcClientDetailsService）或通过ClientDetailsManager接口（这两种实现ClientDetailsService也实现）来更新运行的应用程序。
    //注意：JDBC服务的架构未与库一起打包（因为在实践中可能需要使用太多变体）
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        log.info("=== >>  to configure client service : " + clients);
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clients.withClientDetails(clientDetailsService);

    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        log.info("=== >>  to configure security server : " + security);
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        log.info("=== >>  to configure endpoints server : " + endpoints);
        //token增强配置
//        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//        tokenEnhancerChain.setTokenEnhancers(
//                Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));

        endpoints
//                .tokenStore(redisTokenStore())
//                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .userDetailsService(userDetailsService);
    }


//    @Bean
//    public TokenStore redisTokenStore() {
//        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix("lion");
//        return tokenStore;
//    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //定义jwttoken的某些属性
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
    	JwtAccessTokenConverterConf jwtAccessTokenConverter = new JwtAccessTokenConverterConf();
        jwtAccessTokenConverter.setSigningKey("lion");
        return jwtAccessTokenConverter;
    }
    
    /**
     * jwt 生成token 定制化处理
     *
     * @return TokenEnhancer
     */
//    @Bean
//    public TokenEnhancer tokenEnhancer() {
//        return (accessToken, authentication) -> {
//            final Map<String, Object> additionalInfo = new HashMap<>(2);
//            additionalInfo.put("license", " come from lion");
//            UserDetailsImpl user = (UserDetailsImpl) authentication.getUserAuthentication().getPrincipal();
//            if (user != null) {
//                additionalInfo.put("userId", user.getUserId());
//            }
//            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//            return accessToken;
//        };
//    }
    

}
