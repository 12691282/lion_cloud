package com.lion.web.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lion.common.bean.TestBean;
import com.lion.common.tools.ResultInfo;


@Component
public class AnalysisHystrix implements AnalysisClientFeign{

	@Override
	public ResultInfo testMap(@RequestBody(required = false) TestBean bean) {
		return ResultInfo.getDefeatResult("test map error");
	}
	
	@Override
    public ResultInfo add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b) {
        return ResultInfo.getDefeatResult("test add error");
    }

 
}
