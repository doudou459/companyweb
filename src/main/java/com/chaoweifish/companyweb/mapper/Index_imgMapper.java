package com.chaoweifish.companyweb.mapper;

import com.chaoweifish.companyweb.pojo.Index_img;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Index_imgMapper {

@Insert("INSERT INTO index_img (ID,tile,url) VALUE (#{ID},#{title},#{url})")
int addIndex_img(@Param("ID") long ID, @Param("title") String title, @Param("url") String url);

@Select("SELECT * FROM index_img")
List<Index_img> getIndex_img();

@Delete("DELETE FROM index_img WHERE ID=#{ID}")
int deleteIndex_img(long ID);
}
