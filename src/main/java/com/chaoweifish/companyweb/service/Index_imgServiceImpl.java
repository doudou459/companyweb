package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.mapper.Index_imgMapper;
import com.chaoweifish.companyweb.pojo.Index_img;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Index_imgServiceImpl implements Index_imgService {
    private Index_imgMapper index_imgMapper;
    @Override
    public String addIndex_img(Index_img index_img) {
        String result ="fail";
        if(index_imgMapper.addIndex_img(index_img.getID(),index_img.getTitle(),index_img.getUrl())==1){
            result="success";
        }
        return result;
    }

    @Override
    public List<Index_img> getIndex_img() {
        List<Index_img> list = new ArrayList<>();
        list = index_imgMapper.getIndex_img();
        return list;
    }

    @Override
    public String deleteIndex_img(long ID) {
        String result ="fail";
        if(index_imgMapper.deleteIndex_img(ID)==1){
            result="success";
        }
        return result;
    }

    @Override
    public String saveIndex_img(JSONArray jsonArray) {
        return null;
    }

    @Override
    public String updateIndex_img(Index_img index_img) {
        return null;
    }
}
