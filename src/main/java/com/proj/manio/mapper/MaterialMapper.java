package com.proj.manio.mapper;

import com.proj.manio.pojo.Material;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MaterialMapper {

    @Select("SELECT * FROM material")
    List<Material> getAllMaterial();

    @Insert("INSERT INTO material(file_name, file_url,uuid) VALUES (#{fileName},#{fileUrl},#{uuid})")
    void addFile(Material m);

    
    @Delete("DELETE FROM material WHERE id = #{id}")
    void deleteFile(Integer id);

    @Select("SELECT * FROM material WHERE id = #{id}")
    Material getUuid(Integer id);
}
