package com.proj.manio.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdUtil {

    /**
     * 根据当前时间和手机号生成用户ID
     * @param phone 手机号
     * @return 用户ID（Integer类型）
     */
    public static Integer generateUserIdByPhone(String phone) {
        // 当前时间转字符串
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        // 拼接手机号和时间
        String raw = phone + timeStr;

        // 转成hash值，保证不会溢出
        int hash = raw.hashCode();

        // 转正数（避免负数）
        return Math.abs(hash);
    }
}
