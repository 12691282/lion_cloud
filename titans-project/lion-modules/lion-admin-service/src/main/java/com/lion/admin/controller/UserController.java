package com.lion.admin.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lion.admin.model.bean.UserBean;
import com.lion.admin.service.SysUserService;
import com.lion.common.core.base.BaseController;
import com.lion.common.tools.ResultInfo;

/**
 * @author lion
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
 
    @Autowired
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



 
}
