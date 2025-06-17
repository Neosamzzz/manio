package com.proj.manio.mapper;

import com.proj.manio.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserMapper {
    @Select("SELECT id, username, name, gender FROM user")
    List<User> selectAllUsers();
}
