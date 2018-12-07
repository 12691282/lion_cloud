package com.lion.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lion.auth.feign.UserService;
import com.lion.auth.util.UserDetailsImpl;
import com.lion.common.bean.UserBean;
import com.lion.common.tools.ResultInfo;


@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResultInfo info = userService.findUserByUsername(username);
        if (!info.isSuccess()) {
            throw new UsernameNotFoundException("用户名不存在或者密码错误");
        }
		return UserDetailsImpl.getUserDetailsImpl(info.getApi_data());
	}

}
