package com.proj.manio.controller.LoginAndRegister;


import com.proj.manio.DTO.UserLogin;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.pojo.Admin;
import com.proj.manio.VO.AdminLoginInfo;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.LoginServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/Login")
@Tag(name="登录通用api")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @PostMapping("/Admin")
    @Operation(summary = "管理员登录")
    public Result<AdminLoginInfo> adminLogin(@RequestBody Admin admin){
        AdminLoginInfo adminInfo = loginServiceImpl.adminLogin(admin);
        log.info("管理员用户正在登录");
        if(adminInfo != null){
            log.info("登录成功");
            return Result.success(adminInfo);
        }
        else{
            return Result.error("账号或密码错误");
        }
    }

    @PostMapping("/User")
    @Operation(summary = "用户登录")
    public Result<UserLoginInfo> UserLogin(@RequestBody UserLogin userLogin){
        UserLoginInfo userLoginInfo = loginServiceImpl.userLogin(userLogin);
        log.info("用户"+userLoginInfo.getId()+"正在登录");
        if(userLoginInfo != null){
            log.info("登录成功");
            return Result.success(userLoginInfo);
        }
        else{
            return Result.error("账号或密码错误");
        }
    }
}
