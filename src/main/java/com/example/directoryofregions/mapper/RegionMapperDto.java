package com.example.directoryofregions.mapper;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegionMapperDto {

    @Mapping(source = "shortName", target = "shortName")
    RegionDto toDto(Region region);

    @Mapping(source = "shortName", target = "shortName")
    Region toEntity(RegionDto regionDto);
}
