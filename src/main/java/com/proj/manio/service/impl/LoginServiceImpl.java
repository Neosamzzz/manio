package com.proj.manio.service.impl;

import com.proj.manio.DTO.UserLogin;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.exception.AuthenticationFailedException;
import com.proj.manio.mapper.LoginMapper;
import com.proj.manio.pojo.Admin;
import com.proj.manio.VO.AdminLoginInfo;
import com.proj.manio.service.LoginService;
import com.proj.manio.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private JWTUtil jwtUtil;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public AdminLoginInfo adminLogin(Admin admin){
        AdminLoginInfo adminLoginInfo = loginMapper.adminLogin(admin);
        if(adminLoginInfo != null){
            String pw = loginMapper.getAdminPassword(adminLoginInfo.getId());
            if(!encoder.matches(admin.getPassword(),pw)){
                throw new AuthenticationFailedException();
            }
            log.info("管理员"+adminLoginInfo.getUsername()+"正在登录");
        }else {
            throw new AuthenticationFailedException();
        }
        String Token = jwtUtil.generateAdminToken(admin);//根据admin生成token
        adminLoginInfo.setToken(Token);//设置token
        LocalDateTime now = LocalDateTime.now();
        loginMapper.updateLastLoginTime(admin.getId(),now);//更新最后登录时间
        return adminLoginInfo;
    }

    @Override
    public UserLoginInfo userLogin(UserLogin userLogin) {
        UserLoginInfo userLoginInfo;
        if(!userLogin.isEmail()){
            userLoginInfo = loginMapper.userLoginByPhone(userLogin);
        }else {
            userLoginInfo = loginMapper.userLoginByEmail(userLogin);
        }
        if(userLoginInfo==null){
            throw new AuthenticationFailedException();
        }
        String pw = loginMapper.getPassword(userLoginInfo.getId());
        if(encoder.matches(userLogin.getPassword(),pw)){
            String Token = jwtUtil.generateUserToken(userLoginInfo);//根据user生成token
            userLoginInfo.setToken(Token);//设置token
            return userLoginInfo;
        }else{
            throw new AuthenticationFailedException();
        }
    }
}
