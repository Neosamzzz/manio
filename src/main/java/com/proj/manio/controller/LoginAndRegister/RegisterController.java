package com.proj.manio.controller.LoginAndRegister;

import com.proj.manio.DTO.UserEmailRegister;
import com.proj.manio.DTO.UserPhoneRegister;
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
public class RegisterController {
    @Autowired
    private RegisterServiceImpl registerServiceImpl;

    @GetMapping("/VarifiPhoneCode/{phone}")
    @Operation(summary = "用户接受验证码")
    public Result<Void> getCodeByPhone(@PathVariable String phone){
        registerServiceImpl.getCodeByPhone(phone);
        return Result.success();
    }

    @GetMapping("/VarifiEmailCode/{email}")
    @Operation(summary = "用户接受验证码")
    public Result<Void> getCodeByEmail(@PathVariable String email){
        registerServiceImpl.getCodeByEmail(email);
        return Result.success();
    }


    @PostMapping("/ConfirmPhoneCode")
    @Operation(summary = "确认验证码")
    public Result<Void> ConfirmPhoneCode(@RequestBody UserPhoneRegister userPhoneRegister){
        registerServiceImpl.ConfirmPhoneCode(userPhoneRegister);
        return Result.success();
    }
    @PostMapping("/ConfirmEmailCode")
    @Operation(summary = "确认验证码")
    public Result<Void> ConfirmPhoneCode(@RequestBody UserEmailRegister userEmailRegister){
        registerServiceImpl.ConfirmEmailCode(userEmailRegister);
        return Result.success();
    }
}
