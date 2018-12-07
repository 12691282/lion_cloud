package com.lion.common.tools;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class ResultInfo implements Serializable  {
 
	private static final long serialVersionUID = -1070343107492904137L;
	//处理成功0
	private static String SUCCESS_MARK = "0";
	//处理失败 1
	private static String DEFEAT_MARK = "1";

	private String api_code;
	
	private Object api_data;
	
	
	private ResultInfo(){
		
	}
	
	public boolean isSuccess(){
		
		return StringUtils.equals(SUCCESS_MARK, api_code);
	}
	
	private ResultInfo(String api_code, Object api_data){
		this.api_code = api_code;
		this.api_data = api_data;
	}
	
	private ResultInfo(String api_code){
		this.api_code = api_code;
	}
	
	public static ResultInfo getSuccessResult(){
		return new ResultInfo(ResultInfo.SUCCESS_MARK);
	}
	
	public static  ResultInfo  getSuccessResult(Object data){
		return new ResultInfo(ResultInfo.SUCCESS_MARK, data);
	}
	
	public static ResultInfo getDefeatResult(){
		return new ResultInfo(ResultInfo.DEFEAT_MARK);
	}
	
	public static ResultInfo getDefeatResult(Object data){
		return new ResultInfo(ResultInfo.DEFEAT_MARK, data);
	}

	public String getApi_code() {
		return api_code;
	}

	public void setApi_code(String api_code) {
		this.api_code = api_code;
	}

	@SuppressWarnings("unchecked")
	public <T> T  getApi_data() {
		return (T)api_data;
	}

	public void setApi_data(Object api_data) {
		this.api_data = api_data;
	}
	

}
