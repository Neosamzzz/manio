package com.proj.manio.exception;

public class NoLoginException extends RuntimeException {
    public NoLoginException() {
        super("用户未登录");
    }
}
