package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chaoweifish.companyweb.contants.Contants;
import com.chaoweifish.companyweb.mapper.ProducesMapper;
import com.chaoweifish.companyweb.pojo.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProducesServiceImpl implements ProducesService {
    @Autowired
    private  ProducesMapper producesMapper;
    @Autowired
    private StoreBase64Img storeBase64Img;
    @Autowired
    private Contants contants;

    @Override
    public String addProduces(Produces produces) {
        String result="fail";
        if(producesMapper.addProduces(produces.getID(),produces.getTitle(),produces.getPictureUrl(),produces.getDetail())==1){
            result="success";
        }
        return result;
    }

    @Override
    public List<Produces> getProduces() {
        List<Produces> list =new ArrayList<>();
        list = producesMapper.selectProduces();
        return list;
    }

    @Override
    public String deleteProduces(long ID) {
        String result ="fail";
        if(producesMapper.deleProduces(ID)==1){
            result="success";
        }
        return result;
    }

    @Override
    public String editProduces(Produces produces) {
        String result = "fail";
        if(producesMapper.editProduces(produces.getID(),produces.getTitle(),produces.getPictureUrl(),produces.getDetail())==1){
            result="success";
        }
        return result;
    }

    @Override
    @Transactional
    public String saveProduces(JSONArray jsonArray) {
        String result="fail";
        int m = jsonArray.size();
        for(int i=0;i<m;i++){
            JSONObject jsonObject =(JSONObject) jsonArray.getJSONObject(i).get("jsonData");
           Produces produces = this.setProduces(jsonObject);
            if(contants.getSTABLE().equals(jsonArray.getJSONObject(i).get("state"))){
                continue;
            }else if(contants.getNEW().equals(jsonArray.getJSONObject(i).get("state"))){
                this.addProduces(produces);
                continue;
            }else if(contants.getDELETE().equals(jsonArray.getJSONObject(i).get("state"))){
                if(!("none".equals(jsonObject.getString("pictureUrl"))||jsonObject.getString("pictureUrl")==null||jsonObject.getString("pictureUrl").isEmpty())){
                    String path = contants.getPICTURE_PATH()+ File.separator+jsonObject.getString("pictureUrl");
                    File file= new File(path);
                    if (file.exists()) {
                        file.delete();
                    }
                }
               this.deleteProduces(produces.getID());
                continue;
            }else if(contants.getEDIT().equals(jsonArray.getJSONObject(i).get("state"))){
                this.editProduces(produces);
                continue;
            }
        }
        result="success";
        return result;
    }

    private Produces setProduces(JSONObject jsonObject){
        Produces produces =new Produces();
        produces.setID(jsonObject.getLong("ID"));
        produces.setTitle(jsonObject.getString("title"));
        produces.setDetail(jsonObject.getString("detail"));
        if(jsonObject.get("pictureUrl")==null||"none".equals(jsonObject.getString("pictureUrl"))||jsonObject.getString("pictureUrl").isEmpty()){
            produces.setPictureUrl("none");
        }else if("data".equals(jsonObject.getString("pictureUrl").substring(0, 4))) {
            int begin = jsonObject.getString("pictureUrl").indexOf("/") + 1;
            int end = jsonObject.getString("pictureUrl").indexOf(";") - 1;
            if ("jpg".equals(jsonObject.getString("pictureUrl").substring(begin, end)) || "jpeg".equals(jsonObject.getString("pictureUrl").substring(begin, end))) {
                produces.setPictureUrl(jsonObject.getString("ID") + ".jpg");
            } else {
                produces.setPictureUrl(jsonObject.getString("ID") + ".png");
            }
            storeBase64Img.storeBase64Img(jsonObject.getString("pictureUrl"), produces.getPictureUrl(), contants.getPICTURE_PATH());
        }else{
            produces.setPictureUrl(jsonObject.getString("pictureUrl"));
        }
        return produces;
    }
}
