package com.proj.manio.util;

public class MillisTimeUtil {

    // 毫秒级
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60 * ONE_SECOND;
    public static final long ONE_HOUR   = 60 * ONE_MINUTE;
    public static final long ONE_DAY    = 24 * ONE_HOUR;
    public static final long ONE_WEEK   = 7 * ONE_DAY;

    // 方便方法
    public static long minutes(int n) {
        return n * ONE_MINUTE;
    }

    public static long hours(int n) {
        return n * ONE_HOUR;
    }

    public static long days(int n) {
        return n * ONE_DAY;
    }

    public static long weeks(int n) {
        return n * ONE_WEEK;
    }
}