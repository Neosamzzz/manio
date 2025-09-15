package com.proj.manio.interceptor;

import com.proj.manio.exception.NoLoginException;
import com.proj.manio.util.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {
    public static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        START_TIME.set(System.currentTimeMillis());

        // 获取用户
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token)) {//有token
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            try {
                Claims claims = jwtUtil.parseToken(token);
                Integer id = claims.get("id", Integer.class);
                log.info("用户 {} 请求", id);
            } catch (Exception ex) {
                log.warn("Token 无效: {}", token);
            }
        }

        String method = request.getMethod();
        String uri = request.getRequestURI();
        System.out.println("[请求开始]"+method+" "+uri);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception e){
        long duration = System.currentTimeMillis() - START_TIME.get();
        String uri = request.getRequestURI();
        System.out.println("[请求结束]"+uri+"时间："+duration+"ms");
        START_TIME.remove();
    }
}
