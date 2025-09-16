package com.proj.manio.service;

import com.aliyuncs.exceptions.ClientException;
import com.proj.manio.pojo.Material;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MaterialService {
    List<Material> getAllMaterial();

    void addFiles(MultipartFile[] files) throws IOException, ClientException;

    @Transactional
    void deleteFiles(Integer[] ids) throws ClientException, IOException;
}
