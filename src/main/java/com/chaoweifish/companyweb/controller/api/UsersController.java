package com.chaoweifish.companyweb.controller.api;

import com.chaoweifish.companyweb.contants.Contants;
import com.chaoweifish.companyweb.service.CarouselService;
import com.chaoweifish.companyweb.service.Index_imgService;
import com.chaoweifish.companyweb.service.StoreFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class UsersController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private Index_imgService index_imgService;
    @Autowired
    private StoreFile storeFile;
    @Autowired
    private Contants contants;
    @RequestMapping("/getCarouselImgs")
    public List getCarouselImgs(){
        List list = carouselService.getCarousel();

        return list;
    }

    @RequestMapping("/getIndexImgs")
    public List getIndexImgs(){
        List list = index_imgService.getIndex_img();
        return list;
    }

    @RequestMapping("/uploadImg")
    public String uploadImg(@RequestParam("pictureImg") MultipartFile file){
        String result = storeFile.storeFile(file);
        return result;
    }

    //实现Spring Boot 的文件下载功能，映射网址为/download
    @RequestMapping("/downloadImg")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // 获取指定目录下的第一个文件
        String path =contants.getPICTURE_PATH() ;
        String fileName = request.getParameter("fileName");
        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            File file = new File(path + "/" + fileName);
            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

}
