package com.proj.manio.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.proj.manio.mapper.MaterialMapper;
import com.proj.manio.pojo.Material;
import com.proj.manio.service.MaterialService;
import com.proj.manio.util.AliyunOSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private StringRedisTemplate  stringRedisTemplate;
    @Autowired
    private AliyunOSS aliyunOSS;


    @Override
    public List<Material> getAllMaterial() {
//        Long changeTime = Long.valueOf(stringRedisTemplate.opsForValue().get("MaterialChanged:"));
//        if(System.currentTimeMillis()-changeTime < MillisTimeUtil.ONE_DAY){// 判断一天内素材有没有变更
//            stringRedisTemplate.opsForValue().set("MaterialChanged:",String.valueOf(System.currentTimeMillis()));
//
//
//        }
        return materialMapper.getAllMaterial();
    }

    @Transactional
    @Override
    public void addFiles(MultipartFile[] files) throws IOException, ClientException {
        for(MultipartFile file : files){

            byte[] File = file.getBytes();
            String fileName = file.getOriginalFilename();

            // 上传oss
            String OSS_NAME = "Material/"+UUID.randomUUID().toString()+fileName;
            String fileUrl = aliyunOSS.uuidUpload(File,OSS_NAME);

            Material m = new Material();
            m.setFileName(fileName);
            m.setFileUrl(fileUrl);

            materialMapper.addFile(m);
        }
    }

    @Transactional
    @Override
    public void deleteFiles(Integer[] ids) throws ClientException, IOException {
        for(Integer id : ids){
            // 查找uuid
            Material m = materialMapper.getUuid(id);

            String OSS_NAME = "Material/"+m.getUuid()+m.getFileName();

            materialMapper.deleteFile(id);
            aliyunOSS.remove(OSS_NAME);
        }
    }
}
