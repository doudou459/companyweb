package com.chaoweifish.companyweb.controller.api;


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
    @RequestMapping("/addCarouselImg")
    public HashMap<String,Object> addCarouselImg(HttpServletRequest request, HttpServletResponse response) {
        HashMap map =new HashMap<String,Object>();
        map.put("loginTime",request.getAttribute("newLoginTime"));
        Carousel_img carousel_img=new Carousel_img();
        carousel_img.setID(Long.parseLong(request.getParameter("ID")));
        carousel_img.setShowType(request.getParameter("showType"));
        carousel_img.setTitle(request.getParameter("title"));

        String result = carouselService.addCarousel(carousel_img);
        map.put("result",result);
        return map;
    }


    public HashMap updateCarouselImg(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    public HashMap deleteCarouselImg(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    public HashMap addIndexImg(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    public HashMap updateIndexImg(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


    public HashMap deleteIndexImg(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
