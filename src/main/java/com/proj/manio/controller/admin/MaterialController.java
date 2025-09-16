package com.proj.manio.controller.admin;

import com.aliyuncs.exceptions.ClientException;
import com.proj.manio.pojo.Material;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.MaterialServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/material")
@Tag(name = "素材管理")
public class MaterialController {

    @Autowired
    private MaterialServiceImpl materialServiceImpl;

    @Operation(summary = "获取全部素材")
    @GetMapping
    public Result<List<Material>> getAllMaterial(){
        return Result.success(materialServiceImpl.getAllMaterial());
    }

    @Operation(summary = "上传新素材")
    @PostMapping
    public Result<Void> addFile(MultipartFile[] files) throws IOException, ClientException {
        materialServiceImpl.addFiles(files);
        return Result.success();
    }

    @Operation(summary = "删除素材")
    @DeleteMapping
    public Result<String> deleteFile(@RequestBody Integer[] id) throws ClientException, IOException {
        materialServiceImpl.deleteFiles(id);
        return Result.success("删除成功");
    }
}
