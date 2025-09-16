package com.proj.manio.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    Integer id;
    String fileName;
    String fileUrl;
    String uuid;
}
