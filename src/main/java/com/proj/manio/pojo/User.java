package com.proj.manio.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;               // 主键ID
    private String firstName;         // 姓
    private String lastName;          // 名
    private String password;          // 密码
    private String email;             // 邮箱
    private Integer region;           // 地区代码（可用于手机号前缀）
    private String phone;             // 手机号
    private String wechatId;          // 微信登录ID
    private String googleId;          // 谷歌登录ID
    private LocalDateTime createTime; // 注册时间
    private LocalDateTime updateTime; // 最后更新时间

    // Getter / Setter 省略，可以用 Lombok 简化
}