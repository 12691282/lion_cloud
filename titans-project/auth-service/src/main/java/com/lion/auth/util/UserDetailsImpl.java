package com.lion.auth.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lion.common.bean.RoleBean;
import com.lion.common.bean.UserBean;
import com.lion.common.constant.CommonConstant;


public class UserDetailsImpl implements UserDetails {

 
	private static final long serialVersionUID = -3911619515641144731L;
	
    private Integer userId;
    private String username;
    private String password;
    private String status;
    private List<RoleBean> roleList;
	
	public static UserDetailsImpl getUserDetailsImpl(UserBean bean) {
		 UserDetailsImpl userDetail = new UserDetailsImpl();
		 userDetail.setUserId(bean.getUserId());
		 userDetail.setUsername(bean.getUsername());
		 userDetail.setPassword(bean.getPassword());
		 userDetail.setStatus(bean.getStatusFlag());
		 userDetail.setRoleList(bean.getRoleList());
		 return userDetail;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.getPassword();
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return StringUtils.equals(CommonConstant.STATUS_FOR_LOCK, this.status);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		return StringUtils.equals(CommonConstant.STATUS_FOR_NORMAL, this.status);
	}
	
	private void setUserId(Integer userId) {
		this.userId = userId;
	}
	private void setUsername(String username) {
		this.username = username;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	private void setStatus(String statusFlag) {
		this.status = statusFlag;
	}
	private void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}

}
