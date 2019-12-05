package com.chaoweifish.companyweb.controller.api;

import com.chaoweifish.companyweb.service.CarouselService;
import com.chaoweifish.companyweb.service.Index_imgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class UsersController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private Index_imgService index_imgService;
    @RequestMapping("/getCarouselImgs")
    public List getCarouselImgs(){
        List list = carouselService.getCarousel();

        return list;
    }

    @RequestMapping("/getIndexImgs")
    public List getIndexImgs(){
        List list = index_imgService.getIndex_img();
        return list;
    }

}
