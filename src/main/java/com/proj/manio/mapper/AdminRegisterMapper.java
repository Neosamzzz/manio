package com.proj.manio.mapper;

import com.proj.manio.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminRegisterMapper {
    @Insert("INSERT INTO admin(username,password,last_login_time) VALUES (#{username},#{password},#{lastLoginTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createAdmin(Admin a);

    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin getAdminByUsername(String username);

    @Select("SELECT * FROM admin WHERE id = #{id}")
    Admin getAdminById(Integer id);

    @Update("UPDATE admin SET password = #{pw} WHERE id = #{ID}")
    void updateAdmin(Integer id, String pw);
}
