package com.proj.manio.exception;

import com.proj.manio.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(HttpServletRequest request, Exception e) {
        // 处理异常，返回统一的错误响应
        e.printStackTrace();
        return Result.error("服务器异常，请稍后再试");
    }
}
