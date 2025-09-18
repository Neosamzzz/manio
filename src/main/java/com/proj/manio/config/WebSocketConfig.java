package com.proj.manio.config;

import com.proj.manio.interceptor.AuthHandshakeInterceptor;
import com.proj.manio.interceptor.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final AuthHandshakeInterceptor authHandshakeInterceptor;
    private final ChatWebSocketHandler chatWebSocketHandler; // 你自己的 WebSocketHandler

    public WebSocketConfig(AuthHandshakeInterceptor authHandshakeInterceptor, ChatWebSocketHandler chatWebSocketHandler) {
        this.authHandshakeInterceptor = authHandshakeInterceptor;
        this.chatWebSocketHandler = chatWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/chat")
                .addInterceptors(authHandshakeInterceptor) // 注册拦截器
                .setAllowedOrigins("*"); // 允许跨域
    }
}
