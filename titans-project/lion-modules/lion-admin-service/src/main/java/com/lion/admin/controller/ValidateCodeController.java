package com.lion.admin.controller;

import com.lion.common.general.base.BaseController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class ValidateCodeController extends BaseController {

    /**
     * 创建验证码
     * @param  request
     * @throws Exception
     */
    @GetMapping("/code/{randomStr}")
    public void createCode(@PathVariable String randomStr){
        log.info(" random code : " + randomStr);
    }

}
