package com.proj.manio.exception;

public class NoLoginException extends RuntimeException {
    public NoLoginException() {
        super("用户未登录");
    }
    public NoLoginException(String s) {
        super(s);
    }
}
