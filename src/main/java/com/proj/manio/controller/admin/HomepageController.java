package com.proj.manio.controller.admin;

import com.proj.manio.VO.CategoryVO;
import com.proj.manio.VO.ProductVO;
import com.proj.manio.pojo.Category;
import com.proj.manio.pojo.Homepage;
import com.proj.manio.pojo.Product;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.HomepageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "首页展示控制")
@RestController
@RequestMapping("/manage/Homepage")
public class HomepageController {
    @Autowired
    private HomepageServiceImpl homepageServiceImpl;

    @Operation(summary = "获取全部分类简略")
    @GetMapping
    public Result<List<CategoryVO>> getAllCategory(){
        return Result.success(homepageServiceImpl.getAllCategory());
    }

    @Operation(summary = "根据类别获取简要可用的product")
    @GetMapping("/{id}")
    public Result<List<ProductVO>> getProductByCId(@PathVariable Integer id){
        return Result.success(homepageServiceImpl.getProductByCId(id));
    }

    @Operation(summary = "更新首页")
    @PostMapping
    public Result<Void> updateHomepage(@RequestBody List<Homepage> homepage){
        homepageServiceImpl.updateHomepage(homepage);
        return Result.success();
    }
    @Operation(summary = "先渲染已有首页内容")
    @GetMapping("/Get")
    public Result<List<Homepage>> getHomePage(){
        return Result.success(homepageServiceImpl.getHomepage());
    }

}
