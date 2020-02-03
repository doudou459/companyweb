package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.pojo.Mydemo;

import java.util.List;

public interface MydemoService {
    String addMydemo(Mydemo mydemo);
    List<Mydemo> getMydemo();
    String deleteMydemo(long ID);
    String saveMydemo(JSONArray jsonArray);
    String updateMydemo(Mydemo mydemo);
}
