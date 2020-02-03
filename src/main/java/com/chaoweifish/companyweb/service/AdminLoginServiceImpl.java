package com.chaoweifish.companyweb.service;

import com.chaoweifish.companyweb.mapper.AdminLoginMapper;
import com.chaoweifish.companyweb.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AdminLoginMapper adminLoginMapper;
    private Admin admin;
/**
* 管理员账号登录服务
* @param loginID  用户登录账号
 * @param loginKey 用户登录密码
 * result 登录结果
 *
* **/
    @Override
    @Transactional
    public HashMap<String, Object> doLogin(String loginID, String loginKey) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        String result = "";
        //通过loginID查询管理员账号信息
        Admin admin = adminLoginMapper.getAdminByLoginID(loginID);
        if (admin == null) {
            result = "wrongID";
            map.put("result", result);
            return map;
        }
        long nowTime = System.currentTimeMillis();
        //检测账号连续密码错误次数，如果同一天内连续错误5次则阻止继续尝试
        if ((admin.getWrongTime() >= 5) && (nowTime - admin.getLastLoginTime() < 86400000L)) {
            result = "wrongTimeFull";
        } else {
            //密码正确，将密码错误次数清零  并向前端返回登录结果，同时返回登录时间戳，用于后续权限验证
            if (admin.getLoginKey().equals(loginKey)) {
                admin.setLoginTime(nowTime);
                admin.setLastLoginTime(nowTime);
                admin.setWrongTime(0);
                result = "success";
                map.put("loginTime", admin.getLoginTime());
                adminLoginMapper.updateAdmin(admin.getLoginTime(), admin.getWrongTime(), admin.getLastLoginTime(), admin.getLoginID());
            } else {
                //密码错误，则递增其密码连续错误次数 记录本次操作时间
                if (nowTime - admin.getLastLoginTime() > 86400000L) {
                    admin.setWrongTime(0);
                }
                admin.setLastLoginTime(nowTime);
                admin.setWrongTime(admin.getWrongTime() + 1);
                result = "wrongKey";
                map.put("wrongTime", admin.getWrongTime());
                adminLoginMapper.updateWrongTime(admin.getWrongTime(),admin.getLastLoginTime(),admin.getLoginID());
            }

        }
        map.put("result", result);
        return map;
    }

    /**
     * 管理员账号修改密码服务
     * @param loginID  登录账号
     * @param oldKey   旧密码
     * @param newKey   新密码
     * **/

    @Override
    @Transactional
    public String changeKey(String loginID, String oldKey, String newKey) {
        String result = "";
        Admin admin = adminLoginMapper.getAdminByLoginID(loginID);
        //通过loginID查询管理员账号信息
        if (admin == null) {
            result = "wrongID";
            return result;
        }
        long nowTime = System.currentTimeMillis();
        //检测账号连续密码错误次数，如果同一天内连续错误5次则阻止继续尝试
        if ((admin.getWrongTime() >= 5) && (nowTime - admin.getLastLoginTime() < 86400000L)) {
            result = "wrongTimeFull";
        } else {
            //密码正确，将密码错误次数清零  并将密码修改为newKey
            if (admin.getLoginKey().equals(oldKey)) {
                admin.setLastLoginTime(nowTime);
                admin.setWrongTime(0);
                admin.setLoginKey(newKey);
                result = "success";
                adminLoginMapper.updateLoginKey(admin.getLoginID(),admin.getLoginKey(),admin.getWrongTime(),admin.getLastLoginTime());
            } else {
                //密码错误，则递增其密码连续错误次数  记录本次操作时间
                if (nowTime - admin.getLastLoginTime() > 86400000L) {
                    admin.setWrongTime(0);
                }
                admin.setLastLoginTime(nowTime);
                admin.setWrongTime(admin.getWrongTime() + 1);
                result = "wrongKey";
                adminLoginMapper.updateWrongTime(admin.getWrongTime(),admin.getLastLoginTime(),admin.getLoginID());
            }

        }
            return null;
    }
}







