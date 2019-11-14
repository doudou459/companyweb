package com.chaoweifish.companyweb.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
/*
* 全局异常捕获器
* */
@ResponseBody
@ControllerAdvice
public class controllerAdvice {
    //捕获自定义异常AuthenticationFail
    @ExceptionHandler(value = AuthenticationFail.class)
    public HashMap<String,Object>authenticationFailHandle(AuthenticationFail authenticationFail){
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("code",authenticationFail.getCode());
        map.put("msg",authenticationFail.getMsg());
        return map;
    }
    @ExceptionHandler(value = IOException.class)
    public String IOExceptionHandle(IOException IOException){
        return IOException.getMessage();
    }
}
