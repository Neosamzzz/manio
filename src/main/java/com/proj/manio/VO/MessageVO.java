package com.proj.manio.VO;

import lombok.Data;

@Data
public class MessageVO {
    Integer userId;
    Integer adminId;
    Integer direction;
    String messageText;
}
