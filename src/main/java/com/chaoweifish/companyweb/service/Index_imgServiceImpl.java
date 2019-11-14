package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chaoweifish.companyweb.contants.Contants;
import com.chaoweifish.companyweb.mapper.Index_imgMapper;
import com.chaoweifish.companyweb.pojo.Index_img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Service
public class Index_imgServiceImpl implements Index_imgService {
    @Autowired
    private Index_imgMapper index_imgMapper;
    @Autowired
    private Contants contants;
    @Autowired
    private StoreBase64Img storeBase64Img;
    /**
     * 保存index_img数据
     * @param jsonArray 前端传递的特定modelData （json数组）
     *
     * modelData:{
     *   ID:行ID
     *   state:数据状态，取值范围:stable、new、edit、delete
     *   jsonData:实际行数据（json格式）
     * }
     *
     **/
    @Override
    @Transactional
    public String saveIndex_img(JSONArray jsonArray) {
        String result = "fail";
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject =(JSONObject) jsonArray.getJSONObject(i).get("jsonData");
            Index_img index_img = this.setIndex_img(jsonObject);
            if(contants.getSTABLE().equals(jsonArray.getJSONObject(i).get("state"))){
                continue;
            }else if(contants.getNEW().equals(jsonArray.getJSONObject(i).get("state"))){
                this.addIndex_img(index_img);
                continue;
            }else if(contants.getEDIT().equals(jsonArray.getJSONObject(i).get("state"))){
                this.updateIndex_img(index_img);
                continue;
            }else if(contants.getDELETE().equals(jsonArray.getJSONObject(i).get("state"))){
                if(!"none".equals(jsonObject.getString("url"))){
                    String path = contants.getPICTURE_PATH()+ File.separator+jsonObject.getString("url");
                    File file= new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                this.deleteIndex_img(index_img.getID());
            }
            result ="success";
        }
        return result;
    }

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
    public String updateIndex_img(Index_img index_img) {
        String result = "fail";
        if(index_imgMapper.updateIndex_img(index_img.getTitle(),index_img.getUrl(),index_img.getID())==1){
            result="success";
        }
        return result;
    }
    /**
     *根据json生成index_img对象
     * @param jsonObject index_img的json形式
     *
     * jsonObject的url字段为base64码的情况下，调用storeBase64Img将图片储存
     * 图片文件储存完成后 返回图片的储存名称，并设置index_img的url为该文件储存名称
     * 图片储存时，根据base64码头部的文件类型，分别以.png  或.jpg储存图片
     *
     * 返回 index_img对象
     **/

    private Index_img setIndex_img(JSONObject jsonObject){
        Index_img index_img = new Index_img();
        index_img.setID(jsonObject.getLong("ID"));
        index_img.setTitle(jsonObject.getString("title"));
        if(jsonObject.get("url")==null||"none".equals(jsonObject.getString("url"))){
            index_img.setUrl("none");
        }else if("data".equals(jsonObject.getString("url").substring(0, 4))){
            int begin =jsonObject.getString("url").indexOf("/")+1;
            int end = jsonObject.getString("url").indexOf(";")-1;
            if("jpg".equals(jsonObject.getString("url").substring(begin,end))||"jpeg".equals(jsonObject.getString("url").substring(begin,end))){
                index_img.setUrl(jsonObject.getString("ID")+".jpg");
            }else{
                index_img.setUrl(jsonObject.getString("ID")+".png");
            }
            storeBase64Img.storeBase64Img(jsonObject.getString("url"),index_img.getUrl(),contants.getPICTURE_PATH());
        }else{
            index_img.setUrl(jsonObject.getString("url"));
        }
        return index_img;
    }



}
