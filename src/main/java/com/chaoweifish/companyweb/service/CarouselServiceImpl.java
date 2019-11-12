package com.chaoweifish.companyweb.service;

import com.chaoweifish.companyweb.mapper.Carousel_imgMapper;
import com.chaoweifish.companyweb.pojo.Carousel_img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class CarouselServiceImpl implements CarouselService {
   @Autowired
    private Carousel_imgMapper carousel_imgMapper;
    @Override
    public String addCarousel(Carousel_img carousel_img) {
        String result="fail";
        if(carousel_imgMapper.addCarousel(carousel_img.getID(),carousel_img.getShowType(),carousel_img.getTitle(),carousel_img.getUrl())==1){
            result="success";
        }
        return result;
    }

    @Override
    public List<Carousel_img> getCarousel() {
        List<Carousel_img> list = new ArrayList<>();
        list = carousel_imgMapper.getCarousel();
        return list;
    }

    @Override
    public String deletCarousel(long ID) {
       String result = "fail";
       if(carousel_imgMapper.deletCarousel(ID)==1){
           result ="success";
       }
        return result;
    }
}
