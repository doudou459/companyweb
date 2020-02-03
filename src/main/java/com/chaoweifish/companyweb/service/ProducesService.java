package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.pojo.Produces;

import java.util.List;

public interface ProducesService {
    String addProduces(Produces produces);
    List<Produces> getProduces();
    String deleteProduces(long ID);
    String editProduces(Produces produces);
    String saveProduces(JSONArray jsonArray);
}
