package com.lion.admin.controller;

 

import com.baomidou.mybatisplus.plugins.Page;
import com.lion.admin.model.bean.UserBean;
import com.lion.admin.service.SysUserService;
import com.lion.common.core.base.BaseController;
import com.lion.common.tools.ResultInfo;
import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import com.xiaoleilu.hutool.io.FileUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
