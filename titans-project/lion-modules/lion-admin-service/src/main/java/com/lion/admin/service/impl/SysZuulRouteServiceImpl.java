package com.lion.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lion.admin.mapper.SysZuulRouteMapper;
import com.lion.admin.service.SysZuulRouteService;
import com.lion.common.constant.CommonConstant;
import com.lion.common.constant.RedisConstant;
import com.lion.common.entity.SysZuulRoute;
import com.xiaoleilu.hutool.collection.CollUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysZuulRouteServiceImpl extends ServiceImpl<SysZuulRouteMapper, SysZuulRoute> implements SysZuulRouteService {
	
    @Autowired
    private RedisTemplate redisTemplate;
	

	@Override
	public void initRouteConfigToRedis() {
		log.info("begin inital route config data ...");
		EntityWrapper<SysZuulRoute> wrapper = new EntityWrapper();
		wrapper.eq(CommonConstant.STATUS_FLAG, CommonConstant.STATUS_FOR_NORMAL);
        List<SysZuulRoute> routeList = this.selectList(wrapper);
        if (CollUtil.isNotEmpty(routeList)) {
            redisTemplate.opsForValue().set(RedisConstant.ROUTE_KEY, routeList);
            log.info("Redis config has ：{} record", routeList.size());
        }
        log.info("inital route config data compelete...");
	}

}
