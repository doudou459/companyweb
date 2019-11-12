package com.chaoweifish.companyweb.controller;


import com.chaoweifish.companyweb.service.AdminLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    //管理员登录地址
    @RequestMapping("/doLogin")
    public HashMap<String, Object> doLogin(HttpServletRequest request, HttpServletResponse response) {
        String loginID = request.getParameter("loginID");
        String loginKey = request.getParameter("loginKey");

        return adminLoginService.doLogin(loginID,loginKey);
    }
    //管理员账号更改密码
    @RequestMapping("/changeKey")
    public String changeKey(HttpServletRequest request, HttpServletResponse response) {
        String loginID = request.getParameter("loginID");
        String oldKey = request.getParameter("oldKey");
        String newKey = request.getParameter("newKey");
        return adminLoginService.changeKey(loginID,oldKey,newKey);
    }
}
