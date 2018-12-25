package com.beta.lion.gate.config;

import com.lion.common.constant.RedisConstant;
import com.lion.common.general.entity.SysZuulRoute;
import com.xiaoleilu.hutool.collection.CollUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


@Slf4j
public class DynamicRouteLocator extends DiscoveryClientRouteLocator {
	
	private ZuulProperties properties;
    @SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	public DynamicRouteLocator(String servletPath, DiscoveryClient discovery, ZuulProperties properties,
			ServiceInstance localServiceInstance, RedisTemplate redisTemplate ) {
		super(servletPath, discovery, properties, localServiceInstance);
		
		this.properties = properties;
		this.redisTemplate = redisTemplate;
	}

	
	   /**
     * 重写路由配置，更新
     * 1. properties 配置。
     * 2. eureka 默认配置。
     * 3. Redis 配置。
     *  
     * @return 路由表
     */
    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        //读取  properties、eureka默认配置
        routesMap.putAll(super.locateRoutes());
       //读取自定义的路由配置
        this.loadRoutesInfoByRedis(routesMap);
        return routesMap;
    }

    /**
     * 加载redis存储的路由信息
     * @param routesMap
     */
	private void loadRoutesInfoByRedis(LinkedHashMap<String, ZuulRoute> routesMap) {
		log.debug("begin load route info ....");
 
		List<SysZuulRoute> routeList = (List<SysZuulRoute>)redisTemplate.opsForValue().get(RedisConstant.ROUTE_KEY);
		
		//redis 未放置路由信息
		if(routeList == null){
			log.debug("route is null ....");
			return;
		}
		
		Boolean hasPrefix =  StrUtil.isNotBlank(this.properties.getPrefix());
		
	    for (SysZuulRoute routeObj : routeList) {
		    String path = routeObj.getPath();
            if (StringUtils.isBlank(path) && StringUtils.isBlank(routeObj.getUrl())) {
                continue;
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
            
            try {
                zuulRoute.setId(routeObj.getServiceId());
                zuulRoute.setPath(path);
                zuulRoute.setServiceId(routeObj.getServiceId());
                zuulRoute.setRetryable(StringUtils.equals(routeObj.getRetryable(), "0") ? Boolean.FALSE : Boolean.TRUE);
                zuulRoute.setStripPrefix(StringUtils.equals(routeObj.getStripPrefix(), "0") ? Boolean.FALSE : Boolean.TRUE);
                zuulRoute.setUrl(routeObj.getUrl());
                List<String> sensitiveHeadersList = StrUtil.splitTrim(routeObj.getSensitiveheadersList(), ",");
                if (sensitiveHeadersList != null) {
                    Set<String> sensitiveHeaderSet = CollUtil.newHashSet();
                    sensitiveHeadersList.forEach(sensitiveHeader -> sensitiveHeaderSet.add(sensitiveHeader));
                    zuulRoute.setSensitiveHeaders(sensitiveHeaderSet);
                    zuulRoute.setCustomSensitiveHeaders(true);
                }
            } catch (Exception e) {
                log.error("从数据库加载路由配置异常", e);
            }
            
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            
            if(hasPrefix){
            	path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            
            log.debug("添加数据库自定义的路由配置,path：{}，serviceId:{}", path, zuulRoute.getServiceId());
            
            routesMap.put(path, zuulRoute);
	    }
		  
	}
}
