package com.proj.manio.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户个人中心")
@RequestMapping("/me")
@RestController
public class UserProfileController {
//    @Autowired
//    private UserProfileServiceImpl userProfileServiceImpl;
//
//    @Operation(summary = "获取用户信息")
//
}
