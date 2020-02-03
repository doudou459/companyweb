package com.chaoweifish.companyweb.mapper;


import com.chaoweifish.companyweb.pojo.Produces;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProducesMapper {
    @Update("INSERT INTO produces (ID,title,pictureUrl,detail) VALUE (#{ID},#{title},#{pictureUrl},#{detail})")
    int addProduces(@Param("ID")long ID, @Param("title") String title, @Param("pictureUrl") String pictureUrl, @Param("detail") String detail);

    @Select("SELECT * FROM produces")
    List<Produces> selectProduces();

    @Update("UPDATE produces SET title=#{title},pictureUrl=#{pictureUrl},detail=#{detail} WHERE ID=#{ID}")
    int editProduces(@Param("ID") long ID,@Param("title") String title,@Param("pictureUrl") String pictureUrl,@Param("detail") String detail);

    @Delete("DELETE FROM produces WHERE ID=#{ID}")
    int deleProduces(@Param("ID") long ID);
}
