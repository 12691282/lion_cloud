package com.lion.auth.feign.fallback;

import org.springframework.stereotype.Service;

import com.lion.auth.feign.UserService;
import com.lion.common.tools.ResultInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceFallbackImpl implements UserService{

	@Override
	public ResultInfo findUserByUsername(String username) {
		log.error("调用{}异常:{}", "findUserByUsername", username);
		return null;
	}

}
