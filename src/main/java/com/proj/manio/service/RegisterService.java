package com.proj.manio.service;

import com.proj.manio.DTO.UserEmailRegister;
import com.proj.manio.DTO.UserPhoneRegister;

public interface RegisterService {
    void getCodeByPhone(String phone);

    void ConfirmPhoneCode(UserPhoneRegister userPhoneRegister);

    void getCodeByEmail(String email);

    void ConfirmEmailCode(UserEmailRegister userEmailRegister);
}
