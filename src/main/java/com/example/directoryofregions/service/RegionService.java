package com.example.directoryofregions.service;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.mapper.RegionMapper;
import com.example.directoryofregions.mapper.RegionMapperDto;
import com.example.directoryofregions.model.Region;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private final RegionMapper regionMapper;

    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Cacheable(value = "regions")
    public List<RegionDto> findAll() {
        return regionMapper.findAll().stream()
                .map(RegionMapperDto.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "region", key = "#id")
    public RegionDto findById(Long id) {
        Region region = regionMapper.findById(id);
        return RegionMapperDto.INSTANCE.toDto(region);
    }

    @CacheEvict(value = "regions", allEntries = true)
    public void insert(RegionDto regionDto) {
        // Преобразование DTO в сущность
        Region region = RegionMapperDto.INSTANCE.toEntity(regionDto);
        // Проверка на существование региона перед добавлением
        int existingRegionsCount = regionMapper.countByNameOrShortName(region.getName(), region.getShortName());
        if (existingRegionsCount > 0) {
            throw new IllegalArgumentException("Регион с таким наименованием или сокращенным наименованием уже существует");
        }
        regionMapper.insert(region);
    }

    @CachePut(value = "region", key = "#regionDto.id")
    @CacheEvict(value = "regions", allEntries = true)
    public void update(RegionDto regionDto) {
        // Преобразование DTO в сущность для обновления
        Region region = RegionMapperDto.INSTANCE.toEntity(regionDto);
        regionMapper.update(region);
    }

    @CacheEvict(value = {"regions", "region"}, allEntries = true, key = "#id")
    public void delete(Long id) {
        regionMapper.delete(id);
    }
}
