package com.lion.common.bean;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class RoleBean implements Serializable {
	
	private static final long serialVersionUID = -5146362288947661317L;
	
	private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;
    private String statusFlag;

}
