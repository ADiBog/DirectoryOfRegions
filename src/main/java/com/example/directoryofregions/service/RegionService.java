package com.example.directoryofregions.service;


import com.example.directoryofregions.mapper.RegionMapper;
import com.example.directoryofregions.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionMapper regionMapper;

    @Autowired
    public RegionService(RegionMapper regionMapper) {
        this.regionMapper = regionMapper;
    }

    @Cacheable(value = "regions")
    public List<Region> findAll() {
        return regionMapper.findAll();
    }

    @Cacheable(value = "region", key = "#id")
    public Region findById(Long id) {
        return regionMapper.findById(id);
    }

    @CacheEvict(value = "regions", allEntries = true)
    public void insert(Region region) {
        regionMapper.insert(region);
    }

    @CachePut(value = "region", key = "#region.id")
    @CacheEvict(value = "regions", allEntries = true)
    public void update(Region region) {
        regionMapper.update(region);
    }

    @CacheEvict(value = {"regions", "region"}, allEntries = true, key = "#id")
    public void delete(Long id) {
        regionMapper.delete(id);
    }
}

