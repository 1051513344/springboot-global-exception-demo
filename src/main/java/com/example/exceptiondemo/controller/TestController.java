package com.example.exceptiondemo.controller;

import com.example.exceptiondemo.exception.BizRuntimeException;
import com.example.exceptiondemo.exception.model.AjaxResult;
import com.example.exceptiondemo.exception.model.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class TestController {


    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public AjaxResult<String> test1(
            @RequestParam("fee") String fee
    ){
        log.info("fee={}", fee);
        if("0".equals(fee)){
            throw new BizRuntimeException(ResCode.EXCEPTION_134000);
        }
        return AjaxResult.ok();
    }


}
