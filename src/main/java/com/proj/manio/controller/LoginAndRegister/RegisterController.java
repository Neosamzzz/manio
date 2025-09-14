package com.proj.manio.controller.LoginAndRegister;

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

    @GetMapping("")
}
