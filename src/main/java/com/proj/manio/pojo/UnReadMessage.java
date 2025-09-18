package com.proj.manio.pojo;

import lombok.Data;

@Data
public class UnReadMessage {
    Long id;
    Long messageId;
    Integer userId;
    Integer adminId;
    Integer isNewMessage;
}
