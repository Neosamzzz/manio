package com.proj.manio.service;

import com.proj.manio.VO.AdminLoginInfo;
import com.proj.manio.pojo.Admin;

public interface AdminRegisterService {
    AdminLoginInfo AdminRegister(Admin admin);

    AdminLoginInfo adminAlterPw(Admin admin);
}
