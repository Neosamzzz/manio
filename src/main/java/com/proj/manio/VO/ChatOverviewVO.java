package com.proj.manio.VO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatOverviewVO {
    Integer userId;
    LocalDateTime createTime;
    String messageText;

}
