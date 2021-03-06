package com.lion.admin.model.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserBean implements Serializable{
 
	private static final long serialVersionUID = 6806747198213557097L;
	/**
     * 主键ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 随机盐
     */
    private String salt;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 0-正常，1-删除
     */
    private String statusFlag;
    /**
     * 简介
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Integer deptId;
    /**
     * 部门名称
     */
    private String deptName;
    
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;
    
	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", username=" + username + ", password=" + password + ", salt=" + salt
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", statusFlag=" + statusFlag + ", phone="
				+ phone + ", avatar=" + avatar + ", deptId=" + deptId + ", deptName=" + deptName + "]";
	}


}
