package com.proj.manio.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListVO {
    private int productId;
    private BigDecimal price;
    private String name;
    private String coverImg;
}
