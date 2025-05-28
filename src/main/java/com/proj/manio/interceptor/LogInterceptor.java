package com.proj.manio.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LogInterceptor implements HandlerInterceptor {
    public static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        START_TIME.set(System.currentTimeMillis());
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
