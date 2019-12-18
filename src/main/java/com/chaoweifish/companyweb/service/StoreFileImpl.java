package com.chaoweifish.companyweb.service;

import com.chaoweifish.companyweb.contants.Contants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;
@Service
public class StoreFileImpl implements StoreFile {
    @Autowired
    private Contants contants;
    @Override
    public String storeFile(MultipartFile file) {
        String fileName = System.currentTimeMillis()+getRandomString(6)+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(file.isEmpty()){
            return "false";
        }
       // int size = (int) file.getSize();
        String path =contants.getPICTURE_PATH() ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); //保存文件
            return fileName;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
