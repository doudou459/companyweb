package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.chaoweifish.companyweb.contants.Contants;
import com.chaoweifish.companyweb.mapper.Carousel_imgMapper;
import com.chaoweifish.companyweb.pojo.Carousel_img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class CarouselServiceImpl implements CarouselService {
   @Autowired
    private Carousel_imgMapper carousel_imgMapper;
   @Autowired
   private Contants contants;
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

    @Override
    public String saveCarousel(JSONArray jsonArray) {
        for(int i = 0; i<jsonArray.size(); i++){
            if(jsonArray.getJSONObject(i).get("state").equals(contants.getSTABLE())){
                continue;
            }else if(jsonArray.getJSONObject(i).get("state").equals(contants.getNEW())){

            }else if(jsonArray.getJSONObject(i).get("state").equals(contants.getEDIT())){

            }else if(jsonArray.getJSONObject(i).get("state").equals(contants.getDELETE())){

            }

        }

        return null;
    }


    /**
     * base64转文件并输出到指定目录
     * @param base64Str  图片的base64码
     * @param fileName   图片名称
     * @param filePath   图片保存地址
     * @return
     */
    public String storeBase64Img(String base64Str,String fileName,String filePath){
        File file = null;
        String result = "fail";
        //创建文件目录
        File  dir=new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        byte[] b = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            b = decoder.decodeBuffer(base64Str);
           // window
            file=new File(filePath+File.separator+fileName);
           //linux
           //file=new File(filePath+"/"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(b);
            result = "success";
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }



}
