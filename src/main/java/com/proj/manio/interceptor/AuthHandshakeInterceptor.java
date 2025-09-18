package com.proj.manio.interceptor;

import com.proj.manio.exception.NoLoginException;
import com.proj.manio.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Map<String, Object> attributes) {

        String token = null;
        List<String> authHeaders = request.getHeaders().get("Authorization");
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String authHeader = authHeaders.get(0);
            if (authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }

        // 2. 如果 Header 没有，再看 query 参数
        if (token == null) {
            String query = request.getURI().getQuery();
            if (query != null && query.startsWith("token=")) {
                token = query.substring(6);
            }
        }

        if (token != null) {
            Claims claims = jwtUtil.parseToken(token);
            attributes.put("userId", claims.get("id", Integer.class));
            attributes.put("identityType", claims.get("identityType", String.class));
        } else {
            throw new NoLoginException();
        }
        return true;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request, ServerHttpResponse response,
            WebSocketHandler wsHandler, Exception ex) {}
}
