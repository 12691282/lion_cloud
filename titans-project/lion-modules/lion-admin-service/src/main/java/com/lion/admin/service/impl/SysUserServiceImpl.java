package com.lion.admin.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lion.admin.mapper.SysUserMapper;
import com.lion.admin.model.bean.UserBean;
import com.lion.admin.model.entity.SysUser;
import com.lion.admin.service.SysUserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>  implements SysUserService {
	
	private final SysUserMapper sysUserMapper;

	@Override
	public UserBean findUserInfo(UserBean bean) {
		log.info("findUserInfo bean info " + bean);
		SysUser query = new SysUser();
		query.setUsername(bean.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(query);
        UserBean newBean = new UserBean();
        BeanUtils.copyProperties(sysUser, newBean);
		return newBean;
	}

	@Override
	public UserBean findUserByUsername(String username) {
		UserBean bean = new UserBean();
		bean.setUsername(username);
		return this.findUserInfo(bean);
	}

}
