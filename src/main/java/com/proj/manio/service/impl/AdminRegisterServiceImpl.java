package com.proj.manio.service.impl;

import com.proj.manio.VO.AdminLoginInfo;
import com.proj.manio.exception.NormalException;
import com.proj.manio.mapper.AdminRegisterMapper;
import com.proj.manio.pojo.Admin;
import com.proj.manio.service.AdminRegisterService;
import com.proj.manio.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.apache.coyote.http11.Constants.a;

@Component
public class AdminRegisterServiceImpl implements AdminRegisterService {
    @Autowired
    private AdminRegisterMapper adminRegisterMapper;

    @Transactional
    @Override
    public AdminLoginInfo AdminRegister(Admin admin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//加密工具
        // 查找有无重复用户名
        if(adminRegisterMapper.getAdminByUsername(admin.getUsername())!=null){
            throw new NormalException("用户名已存在");
        }

        String pw = encoder.encode(admin.getPassword());
        Admin a = new Admin();
        a.setUsername(admin.getUsername());
        a.setPassword(pw);
        a.setLastLoginTime(LocalDateTime.now());

        adminRegisterMapper.createAdmin(a);
        Integer newAdminId = a.getId();

        AdminLoginInfo adminLoginInfo = new AdminLoginInfo();
        adminLoginInfo.setId(newAdminId);
        adminLoginInfo.setUsername(a.getUsername());
        adminLoginInfo.setToken(JWTUtil.generateAdminToken(a));

        return adminLoginInfo;
    }

    @Transactional
    @Override
    public AdminLoginInfo adminAlterPw(Admin admin) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();//加密工具

        Integer ID = admin.getId();
        Admin a = adminRegisterMapper.getAdminById(ID);// 查找用户
        if(a==null){
            throw new NormalException("用户不存在");
        }

        String pw = encoder.encode(admin.getPassword());

        adminRegisterMapper.updateAdmin(ID,pw);// 更新

        // 生成新token
        AdminLoginInfo adminLoginInfo = new AdminLoginInfo();
        adminLoginInfo.setId(ID);
        adminLoginInfo.setUsername(a.getUsername());
        adminLoginInfo.setToken(JWTUtil.generateAdminToken(a));

        return adminLoginInfo;
    }
}
