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

    @ExceptionHandler(NormalException.class)
    public Result<String> handleException(HttpServletRequest request, NormalException e) {
        e.printStackTrace();
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoLoginException.class)//未登录异常
    public Result<String> handleException(HttpServletRequest request,NoLoginException e){
        e.printStackTrace();
        return Result.error("用户未登录，请登录");
    }

    @ExceptionHandler(AuthenticationFailedException.class)//未登录异常
    public Result<String> handleException(HttpServletRequest request, AuthenticationFailedException e){
        e.printStackTrace();
        return Result.error("账号或密码错误");
    }

    @ExceptionHandler(ProductNotExistException.class)//未登录异常
    public Result<String> handleException(HttpServletRequest request, ProductNotExistException e){
        e.printStackTrace();
        return Result.error("商品不存在");
    }
}
