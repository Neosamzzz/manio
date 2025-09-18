package com.proj.manio.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    Long id;
    Integer userId;
    Integer adminId;
    LocalDateTime createTime;
    String messageText;
    Integer Direction;// 0:FROM user TO admin;1:FROM admin TO user
}
