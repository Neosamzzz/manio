package com.proj.manio.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    int productId;
    String coverImg;
    String name;
    BigDecimal price;
    int quantity;
}
