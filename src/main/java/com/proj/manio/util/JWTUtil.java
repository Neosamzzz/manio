package com.proj.manio.util;

import com.proj.manio.DTO.UserLogin;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.pojo.Admin;
import com.proj.manio.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET_KEY = "Hyberiid";//密钥

    public static String generateAdminToken(Admin admin) {//生成管理员token
        return Jwts.builder()
                .setSubject("admin-login")
                .claim("id", admin.getId())
                .claim("username", admin.getUsername())
                .claim("identityType","admin")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1小时
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String generateUserToken(UserLoginInfo userLoginInfo) {//生成token
        return Jwts.builder()
                .setSubject("user-login")
                .claim("id", userLoginInfo.getId())
                .claim("identityType","user")
                .setExpiration(new Date(System.currentTimeMillis() + 3600000*24*7)) // 1周过期天数
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {//解析token
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
