package com.chaoweifish.companyweb.controller.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.pojo.Carousel_img;
import com.chaoweifish.companyweb.service.CarouselService;
import com.chaoweifish.companyweb.service.Index_imgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
/*
该类下所有方法均通过AOP进行权限验证
通知类:com.chaoweifish.companyweb.advice.AuthenticationAdvice
权限验证不通过将拦截请求(抛出自定义异常，终止请求执行，自定义异常拦截器向前端反馈异常信息)
权限验证通过后将返回newLoginTime
需将newLoginTime作为loginTime 重新返回给前端
*/
@RestController
public class ManageController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private Index_imgService index_imgService;

    @RequestMapping("/saveCarouselImg")
    public HashMap<String,Object> saveCarouselImg(HttpServletRequest request, HttpServletResponse response) {
        HashMap map =new HashMap<String,Object>();
        map.put("loginTime",request.getAttribute("newLoginTime"));
        JSONArray jsonArray = JSON.parseArray(request.getParameter("carouselData"));
        String result = carouselService.saveCarousel(jsonArray);
        map.put("result",result);
        return map;
    }

    @RequestMapping("/saveIndexImg")
    public HashMap<String,Object> saveIndexImg(HttpServletRequest request, HttpServletResponse response) {
        HashMap map =new HashMap<String,Object>();
        map.put("loginTime",request.getAttribute("newLoginTime"));
        JSONArray jsonArray = JSON.parseArray(request.getParameter("indexImgData"));
        String result = index_imgService.saveIndex_img(jsonArray);
        map.put("result",result);
        return map;
    }
}
