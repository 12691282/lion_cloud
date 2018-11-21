package com.lion.web.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lion.bean.TestBean;
import com.lion.tools.ResultInfo;

@FeignClient(value="analysis-service", fallback = AnalysisHystrix.class)
public interface AnalysisClientFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/test/map")
	ResultInfo testMap(@RequestBody(required = false) TestBean bean);
	
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    ResultInfo add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
	
}
