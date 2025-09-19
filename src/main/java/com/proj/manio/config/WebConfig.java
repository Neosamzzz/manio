package com.proj.manio.config;


import com.proj.manio.interceptor.AdminLoginInterceptor;
import com.proj.manio.interceptor.LogInterceptor;
import com.proj.manio.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LogInterceptor logInterceptor;

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有路径
                .allowedOriginPatterns("*") // 允许所有来源
                .allowedMethods("*") // GET、POST、PUT...
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors (InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(logInterceptor)
                .addPathPatterns("/**");

        interceptorRegistry.addInterceptor(adminLoginInterceptor)//注册管理员登录拦截器
                .addPathPatterns("/manage/**","/upload","/deletefile")
                .excludePathPatterns("/Login/Admin","/AdminRegister");
//
        interceptorRegistry.addInterceptor(userLoginInterceptor)//注册用户登录拦截器
                .addPathPatterns("/me", "/cart","/userChat") // 用户功能需要登录
        .excludePathPatterns("/Login/User", "/register/**"); // 登录注册放行
//}
    }




}
