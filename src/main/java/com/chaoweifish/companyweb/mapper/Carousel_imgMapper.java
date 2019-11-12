package com.chaoweifish.companyweb.mapper;

import com.chaoweifish.companyweb.pojo.Carousel_img;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Carousel_imgMapper {

   @Insert("INSERT INTO carousel_img (ID,showType,title,url) VALUE (#{ID},#{showType},#{title},#{url})")
   int addCarousel(@Param("ID") long ID, @Param("showType") String showType,@Param("title") String title,@Param("url") String url);

   @Select("SELECT * FROM carousel_img")
   List<Carousel_img> getCarousel();

   @Delete("DELETE FROM carousel_img WHERE ID=#{ID}")
   int deletCarousel(@Param("ID") long ID);
}
