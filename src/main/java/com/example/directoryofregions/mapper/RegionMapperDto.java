package com.example.directoryofregions.mapper;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegionMapperDto {
    RegionMapperDto INSTANCE = Mappers.getMapper(RegionMapperDto.class);

    @Mapping(source = "shortName", target = "shortName")
    RegionDto toDto(Region region);

    @Mapping(source = "shortName", target = "shortName")
    Region toEntity(RegionDto regionDto);
}
