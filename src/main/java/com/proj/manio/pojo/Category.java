package com.proj.manio.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String imageUrl;
    private Integer parentId;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
}
