package com.proj.manio.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailVO {
    private int productId;
    private String name;
    private BigDecimal price;
    private String description;
    private String[] imgs;
}
