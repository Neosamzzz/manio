package com.proj.manio.service;

import com.proj.manio.DTO.UserEmailRegister;
import com.proj.manio.DTO.UserPhoneRegister;
import com.proj.manio.VO.UserLoginInfo;

public interface RegisterService {
    void getCodeByPhone(String phone);

    UserLoginInfo ConfirmPhoneCode(UserPhoneRegister userPhoneRegister);

    void getCodeByEmail(String email);

    UserLoginInfo ConfirmEmailCode(UserEmailRegister userEmailRegister);
}
