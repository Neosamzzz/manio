package com.proj.manio.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException() {
        super("商品不存在");
    }
}
