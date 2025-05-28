package com.proj.manio.controller;

import com.proj.manio.DTO.UserDTO;
import com.proj.manio.mapper.UserMapper;
import com.proj.manio.pojo.Result;
import com.proj.manio.pojo.User;
import com.proj.manio.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name="用户相关接口")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping
    @Operation(summary = "新增用户")
    public Result<Void> saveUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("收到用户信息"+userDTO);
        return Result.success();
    }
}
