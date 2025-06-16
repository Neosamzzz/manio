package com.proj.manio.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "用户数据传输对象")
public class UserLogin {

    @NotBlank(message = "不能为空")
    @Schema(description = "手机号或者邮箱")
    private String phoneOrEmail;

    @NotBlank(message = "不能为空")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "非空")
    @Schema(description = "登录账号是手机时为true，邮箱为false")
    private boolean isPhone;
}
