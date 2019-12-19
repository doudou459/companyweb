package com.chaoweifish.companyweb.controller.adminAPI;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.service.CarouselService;
import com.chaoweifish.companyweb.service.Index_imgService;
import com.chaoweifish.companyweb.service.StoreFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public HashMap<String,Object> saveCarouselImg(@RequestBody Map<String,Object> jsonMap) {
        HashMap map =new HashMap<String,Object>();
        map.put("loginTime",jsonMap.get("newLoginTime"));
        JSONArray jsonArray = JSON.parseArray((String) jsonMap.get("carouselData"));
        if(jsonArray==null||jsonArray.size()==0){
            map.put("result","success");
        }else {
            String result = carouselService.saveCarousel(jsonArray);
            map.put("result", result);
        }
        return map;
    }


    @RequestMapping("/saveIndexImg")
    public HashMap<String,Object> saveIndexImg(@RequestBody Map<String,Object> jsonMap) {
        HashMap map =new HashMap<String,Object>();
        map.put("loginTime",jsonMap.get("newLoginTime"));
        JSONArray jsonArray =JSON.parseArray((String) jsonMap.get("indexImgData"));
        if(jsonArray==null||jsonArray.size()==0){
            map.put("result","success");
        }else {
            String result = index_imgService.saveIndex_img(jsonArray);
            map.put("result", result);
        }
        return map;
    }


}
