package com.lion.web.core.base;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class BaseController {

	protected Logger logger = null; 
	
	public BaseController(){
		  logger = Logger.getLogger(getClass().getName());
	}
	
	public Logger getLogger(){
		return logger;
	}
	
	protected Map failureInfo(Object data) {
		Map info = new HashMap();
		info.put("info", "0");
		info.put("data", data);
		return info;
	}
	
	protected Map successInfo(Object data) {
		Map info = new HashMap();
		info.put("info", "1");
		info.put("data", data);
		return info;
	}

 
}
