package com.example.directoryofregions.service;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.exception.RegionExistsException;
import com.example.directoryofregions.mapper.RegionMapper;
import com.example.directoryofregions.mapper.RegionMapperDto;
import com.example.directoryofregions.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionService {

    private final RegionMapper regionMapper;
    private static final Logger logger = LoggerFactory.getLogger(RegionService.class);

    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Cacheable(value = "regions")
    public List<RegionDto> findAll() {
        List<RegionDto> regions = regionMapper.findAll().stream()
                .map(RegionMapperDto.INSTANCE::toDto)
                .collect(Collectors.toList());
        logger.info("Все регионы успешно получены. Количество регионов: {}", regions.size());
        return regions;
    }

    @Cacheable(value = "region", key = "#id")
    public RegionDto findById(Long id) {
        Region region = regionMapper.findById(id);
        logger.info("Регион с ID {} успешно найден: {}", id, region.getName());
        return RegionMapperDto.INSTANCE.toDto(region);
    }

    @CacheEvict(value = "regions", allEntries = true)
    public void insert(RegionDto regionDto) {
        // Проверка на null значений name и shortName
        if (regionDto.getName() == null || regionDto.getShortName() == null) {
            throw new IllegalArgumentException("Имя региона и сокращенное имя не могут быть null");
        }
        Region region = RegionMapperDto.INSTANCE.toEntity(regionDto);
        int existingRegionsCount = regionMapper.countByNameOrShortName(region.getName(), region.getShortName());
        if (existingRegionsCount > 0) {
            throw new RegionExistsException("Регион с таким наименованием или сокращенным наименованием уже существует");
        }
        regionMapper.insert(region);
        logger.info("Регион с наименованием {} успешно добавлен.", region.getName());
    }

    @CachePut(value = "region", key = "#regionDto.id")
    @CacheEvict(value = "regions", allEntries = true)
    public void update(RegionDto regionDto) {
        Region region = RegionMapperDto.INSTANCE.toEntity(regionDto);
        regionMapper.update(region);
        logger.info("Регион с ID {} успешно обновлён. Новое наименование: {}", region.getId(), region.getName());
    }

    @CacheEvict(value = {"regions", "region"}, allEntries = true, key = "#id")
    public void delete(Long id) {
        regionMapper.delete(id);
        logger.info("Регион с ID {} успешно удалён.", id);
    }
}
