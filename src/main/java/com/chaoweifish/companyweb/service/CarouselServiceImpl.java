package com.chaoweifish.companyweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chaoweifish.companyweb.contants.Contants;
import com.chaoweifish.companyweb.mapper.Carousel_imgMapper;
import com.chaoweifish.companyweb.pojo.Carousel_img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CarouselServiceImpl implements CarouselService {
   @Autowired
    private Carousel_imgMapper carousel_imgMapper;
   @Autowired
   private Contants contants;

    /**
     * 保存Carousel数据
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
    public String saveCarousel(JSONArray jsonArray) {
        String result = "fail";
        for(int i = 0; i<jsonArray.size(); i++){
            JSONObject carousel = (JSONObject)jsonArray.getJSONObject(i).get("jsonData");
            Carousel_img carousel_img = this.setCarousel(carousel);
            if(contants.getSTABLE().equals(jsonArray.getJSONObject(i).get("state"))){
                continue;
            }else if(contants.getNEW().equals(jsonArray.getJSONObject(i).get("state"))){
                this.addCarousel(carousel_img);
                continue;
            }else if(contants.getEDIT().equals(jsonArray.getJSONObject(i).get("state"))){
                this.updateCarousel(carousel_img);
                continue;
            }else if(contants.getDELETE().equals(jsonArray.getJSONObject(i).get("state"))){
               if(!"none".equals(carousel.getString("url"))){
                   String path = contants.getPICTURE_PATH()+File.separator+carousel.getString("url");
                   File file= new File(path);
                   if (file.exists()) {
                       file.delete();
                   }
               }
               this.deletCarousel(carousel_img.getID());
            }
            result ="success";
        }
        return result;
    }

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
    public String updateCarousel(Carousel_img carousel_img) {
        String result="fail";
        if(carousel_imgMapper.updateCarousel(carousel_img.getShowType(),carousel_img.getTitle(),carousel_img.getUrl(),carousel_img.getID())==1){
            result="success";
        }
        return result;
    }
/**
 *根据json生成Carousel_img对象
 * @param carousel Carousel_img的json形式
 *
 * carousel的url字段为base64码的情况下，调用storeBase64Img方法将图片储存
 * 图片文件储存完成后 返回图片的储存名称，并设置Carousel_img的url为该文件储存名称
 * 图片储存时，根据base64码头部的文件类型，分别以.png  或.jpg储存图片
 *
 * 返回 Carousel_img对象
 **/

      private Carousel_img setCarousel(JSONObject carousel){
          Carousel_img carousel_img = new Carousel_img();
          carousel_img.setID(carousel.getLong("ID"));
          carousel_img.setShowType(carousel.getString("showType"));
          carousel_img.setTitle(carousel.getString("title"));
          if(carousel.get("url")==null||"none".equals(carousel.getString("url"))){
              carousel_img.setUrl("none");
          }else if("data".equals(carousel.getString("url").substring(0, 4))){
              int begin =carousel.getString("url").indexOf("/")+1;
              int end = carousel.getString("url").indexOf(";")-1;
              if("jpg".equals(carousel.getString("url").substring(begin,end))||"jpeg".equals(carousel.getString("url").substring(begin,end))){
                  carousel_img.setUrl(carousel.getString("ID")+".jpg");
              }else{
                  carousel_img.setUrl(carousel.getString("ID")+".png");
              }
              this.storeBase64Img(carousel.getString("url"),carousel_img.getUrl(),contants.getPICTURE_PATH());
          }else{
              carousel_img.setUrl(carousel.getString("url"));
          }
        return carousel_img;
      }



    /**
     * base64转文件并输出到指定目录
     * @param base64Str  图片的base64码
     * @param fileName   图片名称
     * @param filePath   图片保存地址
     * @return
     */
    private void storeBase64Img(String base64Str,String fileName,String filePath){
        File file = null;
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
    }



}
