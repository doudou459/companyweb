package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;

import com.chaoweifish.companyweb.pojo.Carousel_img;

import java.util.HashMap;
import java.util.List;

public interface CarouselService {
    String addCarousel(Carousel_img carousel_img);
    List<Carousel_img>getCarousel();
    String deletCarousel(long ID);
    String saveCarousel(JSONArray jsonArray);
    String updateCarousel(Carousel_img carousel_img);

}
