package com.proj.manio.mapper;

import com.proj.manio.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

public interface RegisterMapper {
    @Select("SELECT COUNT(*) FROM user WHERE phone = #{phone}")
    int PhoneAlreadyRegister(String phone);

    @Insert("INSERT INTO user(id,phone,email,create_time) VALUES (#{id},#{phone},#{email},#{createTime})")
    void CreateUser(User user);
}
