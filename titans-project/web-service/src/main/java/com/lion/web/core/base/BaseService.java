package com.lion.web.core.base;


import org.apache.log4j.Logger;

public class BaseService {

	protected Logger logger = null; 
	
	public BaseService(){
		  logger = Logger.getLogger(getClass().getName());
	}
	
	public Logger getLogger(){
		return logger;
	}
	
	
}
