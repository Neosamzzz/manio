package com.proj.manio.util;

import com.proj.manio.exception.NoLoginException;

public class UserHolder {
    private static final ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void set(Integer userId){
        tl.set(userId);
    }

    public static Integer get(){//获取当前用户id
        Integer id = tl.get();
        if (id == null){
            throw new NoLoginException();//未登录异常
        }
        return id;
    }

    public static void remove(){
        tl.remove();
    }
}
