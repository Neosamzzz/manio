package com.proj.manio.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("账号验证异常");
    }
}
