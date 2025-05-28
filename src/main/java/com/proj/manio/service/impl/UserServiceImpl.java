package com.proj.manio.service.impl;

import com.proj.manio.mapper.UserMapper;
import com.proj.manio.pojo.User;
import com.proj.manio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }
}
