package com.proj.manio.controller.admin;

import com.proj.manio.VO.AdminLoginInfo;
import com.proj.manio.pojo.Admin;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.AdminRegisterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AdminRegister")
@Tag(name = "管理员注册")
public class AdminRegisterController {
    @Autowired
    private AdminRegisterServiceImpl adminRegisterServiceImpl;

    @Operation(summary = "管理员注册")
    @PostMapping
    public Result<AdminLoginInfo> adminRegister(@RequestBody Admin admin){
        AdminLoginInfo adminLogin = adminRegisterServiceImpl.AdminRegister(admin);
        return Result.success(adminLogin);

    }

    @PutMapping
    @Operation(summary = "管理员密码更改")
    public Result<AdminLoginInfo> adminAlterPw(@RequestBody Admin admin){
        AdminLoginInfo adminLogin = adminRegisterServiceImpl.adminAlterPw(admin);
        return Result.success(adminLogin);

    }
}
