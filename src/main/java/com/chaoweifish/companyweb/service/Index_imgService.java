package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.pojo.Index_img;

import java.util.List;

public interface Index_imgService {
    String addIndex_img(Index_img index_img);
    List<Index_img> getIndex_img();
    String deleteIndex_img(long ID);
    String saveIndex_img(JSONArray jsonArray);
    String updateIndex_img(Index_img index_img);

}
