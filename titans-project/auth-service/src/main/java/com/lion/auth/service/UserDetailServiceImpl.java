package com.lion.auth.service;

import com.lion.auth.feign.UserService;
import com.lion.auth.util.UserDetailsImpl;
import com.lion.common.bean.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * document is here https://www.boraji.com/spring-security-5-custom-userdetailsservice-example
 * @author Administrator
 *
 */

@Slf4j
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("load user name :"+ username);

		UserBean info = userService.findUserByUsername(username);
        if (info == null) {
            throw new UsernameNotFoundException("用户名不存！");
        }
		return UserDetailsImpl.getUserDetailsImpl(info);
	}

}
