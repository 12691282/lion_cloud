package com.lion.analysis.controller.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lion.bean.TestBean;
import com.lion.tools.ResultInfo;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
			
	
	@PostMapping("map")
	public ResultInfo testMap(@RequestBody(required = false) TestBean bean){
		log.info("【testMap】 " + bean);
		Map map = new HashMap();
		map.put("1", 1);
		map.put("2", 2);
		map.put("bean", bean);
		return ResultInfo.getSuccessResult(map);
	}
	
	
}
