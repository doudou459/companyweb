package com.chaoweifish.companyweb.mapper;

import com.chaoweifish.companyweb.pojo.Mydemo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MydemoMapper {
    @Update("INSERT INTO mydemo (ID,title,pictureUrl,detail) VALUE (#{ID},#{title},#{pictureUrl},#{detail})")
    int addMydemo(@Param("ID")long ID,@Param("title") String title,@Param("pictureUrl") String pictureUrl,@Param("detail") String detail);

    @Select("SELECT * FROM mydemo")
    List<Mydemo> selectMydemo();

    @Update("UPDATE mydemo SET title=#{title},pictureUrl=#{pictureUrl},detail=#{detail} WHERE ID=#{ID}")
    int editMydemo(@Param("ID") long ID,@Param("title") String title,@Param("pictureUrl") String pictureUrl,@Param("detail") String detail);

    @Delete("DELETE FROM mydemo WHERE ID=#{ID}")
    int deleMydemo(@Param("ID") long ID);
}
