package com.proj.manio.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginInfo {
    private Integer id;
    private String username;
    private String token;
}
