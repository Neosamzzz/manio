package com.proj.manio.service;

import com.proj.manio.DTO.*;
import com.proj.manio.VO.UserLoginInfo;

public interface RegisterService {
    void getCodeByPhone(String phone);

    UserLoginInfo ConfirmPhoneCode(UserPhoneRegister userPhoneRegister);

    void getCodePhone(String phone);

    void getCodeEmail(String email);

    void getCodeByEmail(String email);

    UserLoginInfo ConfirmEmailCode(UserEmailRegister userEmailRegister);

    UserLoginInfo alterPassword(UserFogetPasswordDTO u);

    void setFirstPassword(FirstPasswordDTO firstPasswordDTO);
}
