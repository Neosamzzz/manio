package com.proj.manio.mapper;

import com.proj.manio.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

public interface RegisterMapper {
    @Select("SELECT COUNT(*) FROM user WHERE phone = #{phone}")
    Integer PhoneAlreadyRegister(String phone);

    @Insert("INSERT INTO user(id,phone,email,create_time) VALUES (#{id},#{phone},#{email},#{createTime})")
    void CreateUser(User user);

    @Select("SELECT password FROM user WHERE phone = #{phoneOrEmail}")
    String getPasswordByPhone(String phoneOrEmail);

    @Select("SELECT password FROM user WHERE email = #{phoneOrEmail}")
    String getPasswordByEmail(String phoneOrEmail);

    @Select("SELECT id FROM user WHERE email = #{pE}")
    Integer getIdByEmail(String pE);

    @Update("UPDATE user SET password = #{pw} WHERE id = #{id}")
    void alterPassword(Integer id,String pw);

    @Select("SELECT id FROM user WHERE phone = #{pE}")
    Integer getIdByPhone(String pE);

    @Select("SELECT COUNT(*) FROM user WHERE email = #{email}")
    Integer emailAlreadyRegister(String email);
}
