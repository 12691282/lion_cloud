package com.lion.admin.service;

import com.lion.admin.model.bean.UserBean;

public interface SysUserService {
	/**
	 * 查询用户信息
	 * @param bean
	 * @return
	 */
	UserBean findUserInfo(UserBean bean);
	/**
	 * 根据用户名查询user
	 * @param username
	 * @return
	 */
	UserBean findUserByUsername(String username);
 

}
