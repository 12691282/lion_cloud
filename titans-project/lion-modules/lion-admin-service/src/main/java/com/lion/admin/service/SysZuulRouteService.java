package com.lion.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.lion.common.entity.SysZuulRoute;

public interface SysZuulRouteService extends IService<SysZuulRoute>{
	
	/**
	 * 初始化route路由信息到redis
	 */
	void initRouteConfigToRedis();

}
