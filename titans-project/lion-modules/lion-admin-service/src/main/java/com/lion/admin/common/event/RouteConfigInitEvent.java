package com.lion.admin.common.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.lion.admin.service.SysZuulRouteService;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态路由加载事件
 * @author lion
 *
 */

@Slf4j
@Component
public class RouteConfigInitEvent {
	
    @Autowired
    private SysZuulRouteService sysZuulRouteService;

	
    /**
     * 初始化路由配置的数据， 容器初始化时触发
     *
     */
    @EventListener(value = {EmbeddedServletContainerInitializedEvent.class})
    public void initRouteConfig() {
    	log.info("Rount Event Start ===>");
    	sysZuulRouteService.initRouteConfigToRedis();
    	log.info("Rount Event End <===");
    	
    }
	
}
