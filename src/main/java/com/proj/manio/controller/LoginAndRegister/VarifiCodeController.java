package com.proj.manio.controller.LoginAndRegister;

import com.proj.manio.DTO.UserEmailRegister;
import com.proj.manio.DTO.UserLogin;
import com.proj.manio.DTO.UserPhoneRegister;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.RegisterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/Register")
@Tag(name="注册通用api")
public class VarifiCodeController {
    @Autowired
    private RegisterServiceImpl registerServiceImpl;

    @GetMapping("/VarifiPhoneCode/{phone}")
    @Operation(summary = "用户接受验证码（phone）")
    public Result<Void> getCodeByPhone(@PathVariable String phone){
        registerServiceImpl.getCodeByPhone(phone);
        return Result.success();
    }

    @GetMapping("/VarifiEmailCode/{email}")
    @Operation(summary = "用户接受验证码（email）")
    public Result<Void> getCodeByEmail(@PathVariable String email){
        registerServiceImpl.getCodeByEmail(email);
        return Result.success();
    }


    @PostMapping("/ConfirmPhoneCode")
    @Operation(summary = "确认验证码（phone）")
    public Result<UserLoginInfo> ConfirmPhoneCode(@RequestBody UserPhoneRegister userPhoneRegister){
        UserLoginInfo ul = registerServiceImpl.ConfirmPhoneCode(userPhoneRegister);
        return Result.success(ul);
    }
    @PostMapping("/ConfirmEmailCode")
    @Operation(summary = "确认验证码（email）")
    public Result<UserLoginInfo> ConfirmPhoneCode(@RequestBody UserEmailRegister userEmailRegister){
        UserLoginInfo ul = registerServiceImpl.ConfirmEmailCode(userEmailRegister);
        return Result.success(ul);
    }
}
