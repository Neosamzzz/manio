package com.proj.manio.util;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

public class IdUtil {

    /**
     * 根据当前时间和手机号生成用户ID
     * @param input 手机号
     * @return 用户ID（Integer类型）
     */
    public static Integer generateUserId(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("输入不能为空");
        }

        // 用 CRC32 算法生成哈希值，结果是 long
        CRC32 crc32 = new CRC32();
        crc32.update(input.getBytes(StandardCharsets.UTF_8));
        long hash = crc32.getValue();

        // 取模保证在 8 位数以内 (小于 100000000)
        return (int) (hash % 100000000);
    }
}
