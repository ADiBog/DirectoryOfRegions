package com.example.directoryofregions.controller;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.service.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<RegionDto> getAllRegions() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public RegionDto getRegionById(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @PostMapping
    public void createRegion(@RequestBody RegionDto regionDto) {
        regionService.insert(regionDto);
    }

    @PutMapping("/{id}")
    public void updateRegion(@PathVariable Long id, @RequestBody RegionDto regionDto) {
        regionDto.setId(id);
        regionService.update(regionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Long id) {
        regionService.delete(id);
    }
}
