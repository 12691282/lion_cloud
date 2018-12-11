package com.lion.auth.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lion.auth.feign.fallback.UserServiceFallbackImpl;
import com.lion.common.tools.ResultInfo;

@FeignClient(name = "lion-admin-service", fallback = UserServiceFallbackImpl.class)
public interface UserService {

	@GetMapping("/user/findUserByUsername/{username}")
	ResultInfo findUserByUsername(@PathVariable("username") String username);
	
}
