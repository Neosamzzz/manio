package com.proj.manio.interceptor;

import com.proj.manio.exception.NoLoginException;
import com.proj.manio.util.JWTUtil;
import com.proj.manio.util.UserHolder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token == null || token.isEmpty()) {//没有token
            throw new NoLoginException();
        }
        token = token.substring(7);
        Claims claims = jwtUtil.parseToken(token);
        Integer Id = claims.get("id", Integer.class);
        String identityType = claims.get("identityType", String.class);
        if (Id == null && identityType.equals("user")) {//token无效或者不是用户账号
            throw new NoLoginException();
        }

        UserHolder.set(Id);
        log.info("用户：{}的请求",Id);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        UserHolder.remove();
    }
}
