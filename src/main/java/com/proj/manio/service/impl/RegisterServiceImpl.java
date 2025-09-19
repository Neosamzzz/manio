package com.proj.manio.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.proj.manio.DTO.*;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.exception.NormalException;
import com.proj.manio.mapper.LoginMapper;
import com.proj.manio.mapper.RegisterMapper;
import com.proj.manio.pojo.User;
import com.proj.manio.service.RegisterService;
import com.proj.manio.util.IdUtil;
import com.proj.manio.util.JWTUtil;
import com.proj.manio.util.RegexUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private LoginMapper loginMapper;

    // 注册发送验证码
    @Override
    public void getCodeByPhone(String phone) {
        //判断是否是手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            throw new NormalException("手机号格式错误");
        }
        // 判断在不在数据库
        Integer e = registerMapper.PhoneAlreadyRegister(phone);
        if(e!=0){
            throw new NormalException("手机号已经注册");
        }
        Integer code  = RandomUtil.randomInt(100000, 1000000);
        log.info("手机："+phone+"验证码："+code);
        stringRedisTemplate.opsForValue().set("Phone:VarifiCode:"+phone,String.valueOf(code),15, TimeUnit.MINUTES);
    }

    // 改密码发送验证码
    @Override
    public void getCodePhone(String phone) {
        //判断是否是手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            throw new NormalException("手机号格式错误");
        }
        // 判断在不在数据库
        Integer e = registerMapper.PhoneAlreadyRegister(phone);
        if(e==0){
            throw new NormalException("手机号未注册");
        }
        Integer code  = RandomUtil.randomInt(100000, 1000000);
        log.info("手机："+phone+"验证码："+code);
        stringRedisTemplate.opsForValue().set("Phone:VarifiCode:"+phone,String.valueOf(code),15, TimeUnit.MINUTES);
    }

    @Override
    public UserLoginInfo ConfirmPhoneCode(UserPhoneRegister userPhoneRegister) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String phone = userPhoneRegister.getPhone();
        Integer code = userPhoneRegister.getCode();
        String codeStr = stringRedisTemplate.opsForValue().get("Phone:VarifiCode:" +phone);
        if (codeStr == null) {
            throw new NormalException("验证码已过期，请重新获取");
        }
        Integer CODE = Integer.valueOf(codeStr);
        if(!code.equals(CODE)){//从redis通过手机号码获取验证码
            throw new NormalException("手机号或者验证码错误");
        }
        User user = new User();
        Integer id = IdUtil.generateUserId(phone);
        user.setId(id);
        user.setPhone(phone);
        user.setPassword(encoder.encode("pw"+RandomUtil.randomInt(100,1000)+System.currentTimeMillis()));// 随机初始密码
        user.setCreateTime(LocalDateTime.now());
        registerMapper.CreateUser(user);
        UserLoginInfo ul = new UserLoginInfo();
        ul.setId(id);
        String Token = JWTUtil.generateUserToken(ul);
        ul.setToken(Token);

        stringRedisTemplate.opsForValue().set("Token:User:"+id,Token,30,TimeUnit.DAYS);// 保留30天Token


        return ul;
    }

    // 注册发送验证码
    @Override
    public void getCodeByEmail(String email) {//判断是否是邮箱
        if(RegexUtils.isEmailInvalid(email)){
            throw new NormalException("邮箱格式错误");
        }
        // 判断在不在数据库
        Integer e = registerMapper.emailAlreadyRegister(email);
        if(e!=0){
            throw new NormalException("该邮箱已经注册");
        }
        Integer code  = RandomUtil.randomInt(100000, 1000000);
        log.info("邮箱："+email+"验证码："+code);
        stringRedisTemplate.opsForValue().set("Email:VarifiCode:"+email,String.valueOf(code),15, TimeUnit.MINUTES);
    }
    // 改密码发送验证码
    @Override
    public void getCodeEmail(String email) {//判断是否是邮箱
        if(RegexUtils.isEmailInvalid(email)){
            throw new NormalException("邮箱格式错误");
        }
        // 判断在不在数据库
        Integer e = registerMapper.emailAlreadyRegister(email);
        if(e==0){
            throw new NormalException("该邮箱未注册");
        }
        Integer code  = RandomUtil.randomInt(100000, 1000000);
        log.info("邮箱："+email+"验证码："+code);
        stringRedisTemplate.opsForValue().set("Email:VarifiCode:"+email,String.valueOf(code),15, TimeUnit.MINUTES);
    }

    @Override
    public UserLoginInfo ConfirmEmailCode(UserEmailRegister userEmailRegister) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String email = userEmailRegister.getEmail();
        Integer code = userEmailRegister.getCode();
        String codeStr = stringRedisTemplate.opsForValue().get("Email:VarifiCode:" + email);
        if (codeStr == null) {
            throw new NormalException("验证码已过期，请重新获取");
        }
        Integer CODE = Integer.valueOf(codeStr);
        if(!code.equals(CODE)){//从redis通过邮箱获取验证码
            throw new NormalException("邮箱或者验证码错误");
        }
        User user = new User();
        Integer id = IdUtil.generateUserId(email);
        user.setId(id);
        user.setEmail(email);
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(encoder.encode("pw"+RandomUtil.randomInt(100,1000)+System.currentTimeMillis()));// 随机初始密码
        registerMapper.CreateUser(user);


        UserLoginInfo ul = new UserLoginInfo();
        ul.setId(id);
        String Token = JWTUtil.generateUserToken(ul);
        ul.setToken(Token);

        stringRedisTemplate.opsForValue().set("Token:User:"+id,Token,30,TimeUnit.DAYS); // 30天Token有效期

        return ul;
    }

    @Override
    public UserLoginInfo alterPassword(UserFogetPasswordDTO u) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String P_E = u.getPhoneOrEmail();
        String NEW_PW = u.getNewPassword();
        Integer id;
        if(u.getIsEmail()){// 验证邮箱
            if(RegexUtils.isEmailInvalid(P_E)){
                throw new NormalException("邮箱格式错误");
            }
            // 验证邮箱存在
            id = registerMapper.getIdByEmail(P_E);
            if(id == null){
                throw new NormalException("邮箱错误或用户不存在");
            }
            // 验证验证码
            String codeStr = stringRedisTemplate.opsForValue().get("Email:VarifiCode:" + P_E);
            if (codeStr == null) {
                throw new NormalException("验证码已过期，请重新获取");
            }
            Integer CODE = Integer.valueOf(codeStr);
            if(!CODE.equals(u.getCode())){
                throw new NormalException("验证码错误");
            }

        }else{  // 验证手机号
            if(RegexUtils.isPhoneInvalid(u.getPhoneOrEmail())){
               throw new NormalException("手机号格式错误");
            }
            // 验证手机号合法性
            id = registerMapper.getIdByPhone(P_E);
            if(id == null){
                throw new NormalException("手机号错误或用户不存在");
            }
            // 验证验证码
            String codeStr = stringRedisTemplate.opsForValue().get("Phone:VarifiCode:" + P_E);
            if (codeStr == null) {
                throw new NormalException("验证码已过期，请重新获取");
            }
            Integer CODE = Integer.valueOf(codeStr);
            if(!CODE.equals(u.getCode())){
                throw new NormalException("验证码错误");
            }
        }
        // 验证新老密码
        String oldPassword = loginMapper.getPassword(id);
        if(encoder.matches(NEW_PW,oldPassword)){
            throw new NormalException("新密码不得与老密码重复");
        }
        //完成验证更改数据库
        String encodedPw = encoder.encode(NEW_PW);
        registerMapper.alterPassword(id,encodedPw);
        stringRedisTemplate.delete("Token:User:"+id);
        UserLoginInfo ul = new UserLoginInfo();
        ul.setId(id);
        String newToken = JWTUtil.generateUserToken(ul);
        ul.setToken(newToken);
        stringRedisTemplate.opsForValue().set("Token:User:"+id,newToken,30,TimeUnit.DAYS);
        return ul;

    }

    // 设置第一个密码
    @Override
    public void setFirstPassword(FirstPasswordDTO firstPasswordDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String pw = firstPasswordDTO.getPassword();
        if(!pw.equals(firstPasswordDTO.getConfirmPassword())){
            throw new NormalException("两个密码不一样");
        }
        pw = encoder.encode(pw);
        Integer id = firstPasswordDTO.getId();
        registerMapper.alterPassword(id,pw);
    }
}
