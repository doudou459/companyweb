package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chaoweifish.companyweb.contants.Contants;
import com.chaoweifish.companyweb.mapper.MydemoMapper;
import com.chaoweifish.companyweb.pojo.Mydemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Service
public class MydemoServiceImpl implements MydemoService {
    @Autowired
    private MydemoMapper mydemoMapper;
    @Autowired
    private Contants contants;
    @Autowired
    private StoreBase64Img storeBase64Img;

    @Override
    public String addMydemo(Mydemo mydemo) {
        String result="fail";
        if(mydemoMapper.addMydemo(mydemo.getID(),mydemo.getTitle(),mydemo.getPictureUrl(),mydemo.getDetail())==1){
            result="success";
        }
        return result;
    }

    @Override
    public List<Mydemo> getMydemo() {
        List<Mydemo> list =new ArrayList<>();
        list = mydemoMapper.selectMydemo();
        return list;
    }

    @Override
    public String deleteMydemo(long ID) {
        String result="fail";
        if(mydemoMapper.deleMydemo(ID)==1){
         result="success";
        }
        return result;
    }

    @Override
    @Transactional
    public String saveMydemo(JSONArray jsonArray) {
        String result = "fail";
        int m = jsonArray.size();
        for(int i=0;i<m;i++){
            JSONObject jsonObject =(JSONObject) jsonArray.getJSONObject(i).get("jsonData");
            Mydemo mydemo = this.setMydemo(jsonObject);
            if(contants.getSTABLE().equals(jsonArray.getJSONObject(i).get("state"))){
                continue;
            }else if(contants.getNEW().equals(jsonArray.getJSONObject(i).get("state"))){
                this.addMydemo(mydemo);
                continue;
            }else if(contants.getDELETE().equals(jsonArray.getJSONObject(i).get("state"))){
                if(!("none".equals(jsonObject.getString("pictureUrl"))||jsonObject.getString("pictureUrl")==null||jsonObject.getString("pictureUrl").isEmpty())){
                    String path = contants.getPICTURE_PATH()+ File.separator+jsonObject.getString("pictureUrl");
                    File file= new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                this.deleteMydemo(mydemo.getID());
                continue;
            }else if(contants.getEDIT().equals(jsonArray.getJSONObject(i).get("state"))){
                this.updateMydemo(mydemo);
                continue;
            }
        }
        result ="success";
        return result;
    }

    @Override
    public String updateMydemo(Mydemo mydemo) {
        String result = "fail";
        if(mydemoMapper.editMydemo(mydemo.getID(),mydemo.getTitle(),mydemo.getPictureUrl(),mydemo.getDetail())==1){
            result="success";
        }
        return result;
    }

    private Mydemo setMydemo(JSONObject jsonObject){
       Mydemo mydemo = new Mydemo();
       mydemo.setID(jsonObject.getLong("ID"));
       mydemo.setTitle(jsonObject.getString("title"));
       mydemo.setDetail(jsonObject.getString("detail"));
       if(jsonObject.get("pictureUrl")==null||"none".equals(jsonObject.getString("pictureUrl"))||jsonObject.getString("pictureUrl").isEmpty()){
           mydemo.setPictureUrl("none");
       }else if("data".equals(jsonObject.getString("pictureUrl").substring(0, 4))) {
           int begin = jsonObject.getString("pictureUrl").indexOf("/") + 1;
           int end = jsonObject.getString("pictureUrl").indexOf(";") - 1;
           if ("jpg".equals(jsonObject.getString("pictureUrl").substring(begin, end)) || "jpeg".equals(jsonObject.getString("pictureUrl").substring(begin, end))) {
               mydemo.setPictureUrl(jsonObject.getString("ID") + ".jpg");
           } else {
               mydemo.setPictureUrl(jsonObject.getString("ID") + ".png");
           }
           storeBase64Img.storeBase64Img(jsonObject.getString("pictureUrl"), mydemo.getPictureUrl(), contants.getPICTURE_PATH());
       }else{
           mydemo.setPictureUrl(jsonObject.getString("pictureUrl"));
       }
       return mydemo;
    }

}
