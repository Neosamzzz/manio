package com.proj.manio.controller;


import com.aliyuncs.exceptions.ClientException;
import com.proj.manio.util.AliyunOSS;
import com.proj.manio.pojo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Tag(name="文件上传公共api")
@RestController
public class UploadCotroller {
    @Autowired
    private AliyunOSS aliyunOSS;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile File) throws IOException, ClientException {
        String fileName = File.getOriginalFilename();
        byte[] file =File.getBytes();

        //调用阿里云OSS
        String url = aliyunOSS.uuidUpload(file,fileName);
        log.info("上传文件");
        return Result.success(url);
    }


}
