package com.proj.manio.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("账号或密码错误");
    }
}
