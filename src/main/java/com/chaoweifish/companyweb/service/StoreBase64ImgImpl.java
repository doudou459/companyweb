package com.chaoweifish.companyweb.service;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
@Service
public class StoreBase64ImgImpl implements StoreBase64Img {

    /**
     * base64转文件并输出到指定目录
     * @param base64Str  图片的base64码
     * @param fileName   图片名称
     * @param filePath   图片保存地址
     * @return
     **/

    @Override
    public void storeBase64Img(String base64Str, String fileName, String filePath) {
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
