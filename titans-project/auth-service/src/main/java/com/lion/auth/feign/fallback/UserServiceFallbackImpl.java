package com.lion.auth.feign.fallback;

import com.lion.common.bean.UserBean;
import org.springframework.stereotype.Service;

import com.lion.auth.feign.UserService;
import com.lion.common.tools.ResultInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceFallbackImpl implements UserService{

	@Override
	public UserBean findUserByUsername(String username) {
		log.error("调用{}异常:{}", "findUserByUsername", username);
		return null;
	}

}
