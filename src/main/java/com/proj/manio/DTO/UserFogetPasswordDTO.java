package com.proj.manio.DTO;

import lombok.Data;

@Data
public class UserFogetPasswordDTO {
    String phoneOrEmail;
    String newPassword;
    Integer code;
    Boolean isEmail;
}
