package com.example.directoryofregions.mapper;

import com.example.directoryofregions.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM regions")
    List<Region> findAll();

    @Select("SELECT * FROM regions WHERE id = #{id}")
    Region findById(@Param("id") Long id);

    @Insert("INSERT INTO regions(name, short_name) VALUES(#{name}, #{shortName})")
    void insert(Region region);

    @Update("UPDATE regions SET name = #{name}, short_name = #{shortName} WHERE id = #{id}")
    void update(Region region);

    @Delete("DELETE FROM regions WHERE id = #{id}")
    void delete(@Param("id") Long id);
}
