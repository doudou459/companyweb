package com.chaoweifish.companyweb.mapper;

import com.chaoweifish.companyweb.pojo.Carousel_img;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface Carousel_imgMapper {

   @Insert("INSERT INTO carousel_img (ID,showType,title,url) VALUE (#{ID},#{showType},#{title},#{url})")
   int addCarousel(@Param("ID") long ID, @Param("showType") String showType,@Param("title") String title,@Param("url") String url);

   @Select("SELECT * FROM carousel_img")
   List<Carousel_img> getCarousel();

   @Delete("DELETE FROM carousel_img WHERE ID=#{ID}")
   int deletCarousel(@Param("ID") long ID);
   @Update("UPDATE carousel_img SET showType=#{showType},title=#{title},url=#{url} WHERE ID=#{ID}")
   int updateCarousel(@Param("showType") String showType,@Param("title") String title,@Param("url") String url,@Param("ID") long ID);
}
