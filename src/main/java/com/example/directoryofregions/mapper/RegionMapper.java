package com.example.directoryofregions.mapper;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.model.Region;
import org.apache.ibatis.annotations.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("SELECT * FROM regions")
    @Results({@Result (property = "shortName", column = "short_name")})
    List<Region> findAll();

    @Select("SELECT * FROM regions WHERE id = #{id}")
    @Results({@Result (property = "shortName", column = "short_name")})
    Region findById(@Param("id") Long id);

    @Insert("INSERT INTO regions(name, short_name) VALUES(#{name}, #{shortName})")
    void insert(Region region);

    @Update("UPDATE regions SET name = #{name}, short_name = #{shortName} WHERE id = #{id}")
    void update(Region region);

    @Delete("DELETE FROM regions WHERE id = #{id}")
    void delete(@Param("id") Long id);

    @Select("SELECT COUNT(*) FROM regions WHERE name = #{name} OR short_name = #{shortName}")
    int countByNameOrShortName(@Param("name") String name, @Param("shortName") String shortName);
}
