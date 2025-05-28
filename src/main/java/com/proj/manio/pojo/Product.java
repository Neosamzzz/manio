package com.proj.manio.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer categoryId;
    private Integer sort;
    private String coverImg;
    private Integer status;
    private LocalDateTime createTime;
}
