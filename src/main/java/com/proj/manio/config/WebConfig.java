package com.proj.manio.config;

import com.proj.manio.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LogInterceptor logInterceptor;

    @Override
    public void addInterceptors (InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(logInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径
                .allowedOriginPatterns("*") // 允许所有来源
                .allowedMethods("*") // GET、POST、PUT...
                .allowedHeaders("*")
                .allowCredentials(true);
    }


}
