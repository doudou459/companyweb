package com.chaoweifish.companyweb.mapper;

import com.chaoweifish.companyweb.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminLoginMapper {
    @Select("select * From admin WHERE loginID=#{loginID}")
    Admin getAdminByLoginID(String loginID);

    @Update("UPDATE admin SET loginKey=#{newKey},wrongTime=#{wrongTime},lastLoginTime=#{lastLoginTime} WHERE loginID=#{loginID}")
    int updateLoginKey(@Param("loginID") String loginID, @Param("newKey") String newKey,@Param("wrongTime") int wrongTime, @Param("lastLoginTime") long lastLoginTime);

    @Update("UPDATE admin SET  loginTime=#{loginTime},wrongTime=#{wrongTime},lastLoginTime=#{lastLoginTime} WHERE loginID=#{loginID}")
    int updateAdmin(@Param("loginTime") long loginTime, @Param("wrongTime") int wrongTime, @Param("lastLoginTime") long lastLoginTime, @Param("loginID") String loginID);

    @Update("UPDATE admin SET wrongTime=#{wrongTime},lastLoginTime=#{lastLoginTime} WHERE loginID=#{loginID}")
    int updateWrongTime(@Param("wrongTime") int wrongTime,@Param("lastLoginTime") long lastLoginTime,@Param("loginID") String loginID);

    @Update("UPDATE admin SET loginTime=#{loginTime} WHERE loginID=#{loginID}")
    int updateLoginTime(@Param("loginID") String loginID,@Param("loginTime") long loginTime);
}
