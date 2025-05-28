package com.proj.manio.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private T data; //数据

    public static <T> Result<T> success() {
        return new Result(200,"success",null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(200,"success",data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(400, msg, null);
    }
}
