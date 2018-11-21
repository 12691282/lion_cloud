package com.lion.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lion.bean.TestBean;
import com.lion.tools.ResultInfo;
import com.lion.web.feign.AnalysisClientFeign;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
    private AnalysisClientFeign  analysisClientFeign;

    @GetMapping("/add")
    public ResultInfo dc() {
        String services = "Services: " + discoveryClient.getServices();
        log.info(services);
        TestBean bean = new TestBean();
        bean.setName(services);
        bean.setId(1);
        return analysisClientFeign.testMap(bean);
    }

}
