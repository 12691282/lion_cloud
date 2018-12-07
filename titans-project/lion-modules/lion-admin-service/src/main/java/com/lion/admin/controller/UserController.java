package com.lion.admin.controller;

 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lion.admin.model.bean.UserBean;
import com.lion.admin.service.SysUserService;
import com.lion.common.core.base.BaseController;
import com.lion.common.tools.ResultInfo;

import lombok.AllArgsConstructor;

/**
 * @author lion
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController extends BaseController {
 
    private SysUserService userService;
 
    /**
     * 获取当前用户信息（角色、权限）
     * 并且异步初始化用户部门信息
     *
     * @param UserBean 当前用户信息
     * @return 用户名
     */
    @GetMapping("/info")
    public ResultInfo user(UserBean bean) {
    	UserBean userInfo = userService.findUserInfo(bean);
        return ResultInfo.getSuccessResult(userInfo);
    }
    
    @GetMapping("/findUserByUsername/{username}")
    public ResultInfo findUserByUsername(@PathVariable String username) {
    	UserBean userInfo = userService.findUserByUsername(username);
    	if(userInfo == null){
    		ResultInfo.getDefeatResult();
    	}
    	return ResultInfo.getSuccessResult(userInfo);
    }
    


 
}
