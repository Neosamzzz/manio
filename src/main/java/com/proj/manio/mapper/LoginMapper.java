package com.proj.manio.mapper;

import com.proj.manio.DTO.UserLogin;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.pojo.Admin;
import com.proj.manio.VO.AdminLoginInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public interface LoginMapper {
    @Select("SELECT * FROM admin WHERE username = #{username}")
    AdminLoginInfo adminLogin(Admin admin);

    @Select("SELECT password FROM admin WHERE id = #{id}")
    String getAdminPassword(Integer id);

    @Update("UPDATE admin SET last_login_time = #{now} WHERE id = #{id}")
    void updateLastLoginTime(Integer id, LocalDateTime now);

    @Select("SELECT * FROM user WHERE phone=#{phoneOrEmail}")
    UserLoginInfo userLoginByPhone(UserLogin userLogin);

    @Select("SELECT * FROM user WHERE email=#{phoneOrEmail}")
    UserLoginInfo userLoginByEmail(UserLogin userLogin);

    @Select("SELECT password FROM user WHERE id = #{id}")
    String getPassword(Integer id);
}
