package com.proj.manio.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    Integer id;
    String uuid;
    String fileName;
    String fileUrl;
}
