package com.chaoweifish.companyweb.mapper;

import com.chaoweifish.companyweb.pojo.Contact;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ContactMapper {
    @Select("SELECT detail FROM contact WHERE id=1")
    Contact getContact();

    @Update("UPDATE contact SET detail=#{detail} WHERE id=1")
    int setContact(@Param("detail") String detail);
}
