package com.proj.manio.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.proj.manio.DTO.UserEmailRegister;
import com.proj.manio.DTO.UserPhoneRegister;
import com.proj.manio.VO.UserLoginInfo;
import com.proj.manio.exception.NormalException;
import com.proj.manio.mapper.RegisterMapper;
import com.proj.manio.pojo.User;
import com.proj.manio.service.RegisterService;
import com.proj.manio.util.IdUtil;
import com.proj.manio.util.JWTUtil;
import com.proj.manio.util.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Override
    public void getCodeByPhone(String phone) {
        //判断是否是手机号
        if(RegexUtils.isPhoneInvalid(phone)){
            throw new NormalException("手机号格式错误");
        }
        // 判断在不在数据库
        if(registerMapper.PhoneAlreadyRegister(phone)!=0){
            throw new NormalException("手机号已经注册");
        }
        Integer code  = RandomUtil.randomInt(100000, 1000000);
        log.info("手机："+phone+"验证码："+code);
        stringRedisTemplate.opsForValue().set("Phone:VarifiCode:"+phone,String.valueOf(code),15, TimeUnit.MINUTES);

    }

    @Override
    public UserLoginInfo ConfirmPhoneCode(UserPhoneRegister userPhoneRegister) {
        String phone = userPhoneRegister.getPhone();
        Integer code = userPhoneRegister.getCode();
        if(!code.equals(Integer.parseInt(stringRedisTemplate.opsForValue().get("Phone:VarifiCode:"+phone)))){//从redis通过手机号码获取验证码
            throw new NormalException("手机号或者验证码错误");
        }
        User user = new User();
        Integer id = IdUtil.generateUserId(phone);
        user.setId(id);
        user.setPhone(phone);
        user.setCreateTime(LocalDateTime.now());
        registerMapper.CreateUser(user);

        UserLoginInfo ul = new UserLoginInfo();
        ul.setId(id);
        ul.setToken(JWTUtil.generateUserToken(ul));


        return ul;
    }

    @Override
    public void getCodeByEmail(String email) {//判断是否是邮箱
        if(RegexUtils.isEmailInvalid(email)){
            throw new NormalException("邮箱格式错误");
        }
        // 判断在不在数据库
        if(registerMapper.PhoneAlreadyRegister(email)!=0){
            throw new NormalException("该邮箱已经注册");
        }
        Integer code  = RandomUtil.randomInt(100000, 1000000);
        log.info("邮箱："+email+"验证码："+code);
        stringRedisTemplate.opsForValue().set("email:VarifiCode:"+email,String.valueOf(code),15, TimeUnit.MINUTES);
    }

    @Override
    public UserLoginInfo ConfirmEmailCode(UserEmailRegister userEmailRegister) {
        String email = userEmailRegister.getEmail();
        Integer code = userEmailRegister.getCode();
        if(!code.equals(Integer.parseInt(stringRedisTemplate.opsForValue().get("email:VarifiCode:"+email)))){//从redis通过手机号码获取验证码
            throw new NormalException("邮箱或者验证码错误");
        }
        User user = new User();
        Integer id = IdUtil.generateUserId(email);
        user.setId(id);
        user.setEmail(email);
        user.setCreateTime(LocalDateTime.now());
        registerMapper.CreateUser(user);


        UserLoginInfo ul = new UserLoginInfo();
        ul.setId(id);
        ul.setToken(JWTUtil.generateUserToken(ul));


        return ul;
    }
}
