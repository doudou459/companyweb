package com.chaoweifish.companyweb.advice;

import com.chaoweifish.companyweb.exception.AuthenticationFail;
import com.chaoweifish.companyweb.mapper.AdminLoginMapper;
import com.chaoweifish.companyweb.pojo.Admin;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/*
* 该类为AOP通知类
* 使用前置通知对前端请求进行权限验证
* 权限验证失败抛出自定义异常:com.chaoweifish.companyweb.exception.AuthenticationFail
* 自定义异常通过全局异常捕获器向前端反馈异常信息
* 全局异常捕获器：com.chaoweifish.companyweb.exception.controllerAdvice
* */
@Component
@Aspect
public class AuthenticationAdvice {
    @Autowired
    private AdminLoginMapper adminLoginMapper;
    @Pointcut(value ="execution(* com.chaoweifish.companyweb.controller.adminAPI.*.*(..))")
    private void myPiont(){

    }
    @Before(value = "myPiont()")
    private Object doAuthentication(JoinPoint joinPoint){
        //获取目标方法中的前端传过来的json参数
        Map<String,Object> jsonMap= ( Map<String,Object>)joinPoint.getArgs()[0];
        //获取前端传过来的loginID 和loginTime,如果未null 则抛出异常:权限不足
        if(jsonMap.get("loginID")==null||jsonMap.get("loginTime")==null){
            throw new AuthenticationFail("Insufficient Access","权限不足");
        }
        //通过loginID 获取admin信息，如未找到admin 则抛出异常:权限不足
        Admin admin = adminLoginMapper.getAdminByLoginID((String)jsonMap.get("loginID"));
        if(admin==null){
            throw new AuthenticationFail("Insufficient Access","权限不足");
        }
        /*
        对比admin的loginTime 和前端传过来的loginTime值是否一致，如不一致，则抛出异常:权限不足
        如对比一致则使用随机函数随机控制修改  admin的loginTime值
        无论是否修改loginTime的值，都将loginTime（新值或旧值）作为newLoginTime传递给目标方法
        */
        if(String.valueOf(admin.getLoginTime()).equals(jsonMap.get("loginTime").toString())){
            if(Math.random()*10+1>6){
                long newLoginTime = System.currentTimeMillis();
                adminLoginMapper.updateLoginTime(jsonMap.get("loginID").toString(),newLoginTime);
                jsonMap.put("newLoginTime",newLoginTime);
            }else{
                jsonMap.put("newLoginTime",jsonMap.get("loginTime"));
            }
        }else{
            throw new AuthenticationFail("Insufficient Access","权限不足");
        }
        return null;
    }
}
