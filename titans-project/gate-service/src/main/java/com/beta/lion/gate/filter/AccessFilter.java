package com.beta.lion.gate.filter;

import com.lion.common.constant.CommonConstant;
import com.lion.common.constant.SecurityConstants;
import com.lion.common.general.entity.SysLog;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
@Slf4j
public class AccessFilter extends ZuulFilter{
	

	@Override
	public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		log.info(String.format(" authentication name : %s  ", authentication.getName()));

		if (authentication != null) {
			ctx.addZuulRequestHeader(SecurityConstants.USER_HEADER, authentication.getName());
			ctx.addZuulRequestHeader(SecurityConstants.ROLE_HEADER, CollectionUtil.join(authentication.getAuthorities(), ","));
		}
		this.saveLog(ctx);
        return null;
	}

	private void saveLog(RequestContext requestContext) {
		HttpServletRequest request = requestContext.getRequest();
		Enumeration<String> headNames = request.getHeaderNames();
		while(headNames.hasMoreElements()){
			String headName = headNames.nextElement();
			System.out.println(headName+":"+request.getHeader(headName));

		}


		String requestUri = request.getRequestURI();
		String method = request.getMethod();
		SysLog sysLog = new SysLog();
		sysLog.setType(CommonConstant.STATUS_FOR_NORMAL);
		sysLog.setRemoteAddr(HttpUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(requestUri));
		sysLog.setMethod(method);
		sysLog.setUserAgent(request.getHeader("user-agent"));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		log.info(" access info : " + sysLog);
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
