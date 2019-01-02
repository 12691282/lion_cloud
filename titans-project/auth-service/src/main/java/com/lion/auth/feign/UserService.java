package com.lion.auth.feign;

import com.lion.auth.feign.fallback.UserServiceFallbackImpl;
import com.lion.common.bean.UserBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "lion-admin-service", fallback = UserServiceFallbackImpl.class)
public interface UserService {

	@GetMapping("/user/findUserByUsername/{username}")
	UserBean findUserByUsername(@PathVariable("username") String username);
	
}
