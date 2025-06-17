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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Tag(name="文件上传公共api")
@RestController
public class UploadCotroller {
    @Autowired
    private AliyunOSS aliyunOSS;

    @Operation(summary = "上传文件",description = "选择是否保存原名，非保存原名时按上传年月时间保存随机名称，文件名为yyyy/MM/xxxxxxx")
    @PostMapping("/upload")
    public Result<List<String>> upload(MultipartFile[] Files,boolean keepOriginalName) throws IOException, ClientException {
        List<String> fileUrl = new ArrayList<>();
        if(keepOriginalName){
            for(MultipartFile File:Files){
                String fileName = File.getOriginalFilename();
                byte[] file =File.getBytes();
                //调用阿里云OSS
                String url = aliyunOSS.uuidUpload(file,fileName);
                log.info("上传文件:{}",fileName);
                fileUrl.add(url);
            }
        }else{
            for(MultipartFile File:Files){
                String fileName = File.getOriginalFilename();
                byte[] file =File.getBytes();

                //文件名称
                String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
                String fileType = fileName.substring(fileName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                fileName = dir+"/"+uuid+fileType;
                //调用阿里云OSS
                String url = aliyunOSS.uuidUpload(file,fileName);
                log.info("上传文件:{}",fileName);
                fileUrl.add(url);
            }
        }
        return Result.success(fileUrl);
    }


}
