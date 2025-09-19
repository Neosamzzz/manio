package com.proj.manio.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    Integer productId;
    String coverImg;
    String name;
    BigDecimal price;
}
