package com.proj.manio.controller.LoginAndRegister;

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
@RequestMapping("/forget")
@Tag(name="重置密码api")
public class ForgetPasswordController {
    @Autowired
    private RegisterServiceImpl registerServiceImpl;



}
