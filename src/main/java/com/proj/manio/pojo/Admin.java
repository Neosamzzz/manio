package com.proj.manio.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Integer id;               // 主键ID
    private String username;          // 管理员用户名
    private String password;          // 密码
    private LocalDateTime lastLoginTime; // 上次登录时间
}
