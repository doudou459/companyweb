package com.chaoweifish.companyweb.controller;


import com.chaoweifish.companyweb.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    //管理员登录地址
    @RequestMapping("/doLogin")
    public HashMap<String, Object> doLogin(@RequestBody Map<String,Object> jsonMap) {
        String loginID = (String)jsonMap.get("loginID");
        String loginKey = (String)jsonMap.get("loginKey");
        return adminLoginService.doLogin(loginID,loginKey);
    }
    //管理员账号更改密码
    @RequestMapping("/changeKey")
    public String changeKey(@RequestBody Map<String,Object> jsonMap) {
        String loginID = (String)jsonMap.get("loginID");
        String oldKey = (String)jsonMap.get("oldKey");
        String newKey = (String)jsonMap.get("newKey");
        return adminLoginService.changeKey(loginID,oldKey,newKey);
    }
}
