package com.proj.manio.controller.user;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.VO.HomepageVO;
import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.HomeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "用户首页")
@RestController
@RequestMapping("/homepage")
public class HomeController {
    @Autowired
    private HomeServiceImpl homeServiceImpl;

    @Operation(summary = "navbar产品分类获取")
    @GetMapping("/CategoryList")
    public Result<List<CategoryVO>> getCategoryVO(){
        return  Result.success(homeServiceImpl.getCategoryVO());
    }

    @GetMapping
    public Result<List<HomepageVO>> getHome(){
        return Result.success(homeServiceImpl.getHome());
    }
}
