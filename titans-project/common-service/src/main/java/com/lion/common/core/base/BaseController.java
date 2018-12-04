package com.lion.common.core.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseController {

	@Autowired
    private HttpServletRequest request;

 
	
}
