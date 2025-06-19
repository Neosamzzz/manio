package com.proj.manio.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private int id;
    @Schema(description = "所属商品")
    private int productId;
    private String imgUrl;
    private int sort;
}
