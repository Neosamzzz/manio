package com.proj.manio.service;

import com.proj.manio.DTO.UserLogin;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.pojo.Admin;
import com.proj.manio.VO.AdminLoginInfo;

public interface LoginService {
    AdminLoginInfo adminLogin(Admin admin);

    UserLoginInfo userLogin(UserLogin userLogin);
}
