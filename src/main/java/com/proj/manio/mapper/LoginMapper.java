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

@Mapper
@Component
public interface LoginMapper {
    @Select("SELECT * FROM admin WHERE username = #{username} AND password = #{password}")
    AdminLoginInfo adminLogin(Admin admin);

    @Update("UPDATE admin SET last_login_time = #{now} WHERE id = #{id}")
    void updateLastLoginTime(Integer id, LocalDateTime now);

    @Select("SELECT * FROM user WHERE phone=#{phoneOrEmail} AND password = #{password}")
    UserLoginInfo userLoginByPhone(UserLogin userLogin);

    @Select("SELECT * FROM user WHERE email=#{phoneOrEmail} AND password = #{password}")
    UserLoginInfo userLoginByEmail(UserLogin userLogin);

}
