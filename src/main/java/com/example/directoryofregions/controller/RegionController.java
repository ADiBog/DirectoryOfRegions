package com.example.directoryofregions.controller;

import com.example.directoryofregions.model.Region;
import com.example.directoryofregions.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public Region getRegionById(@PathVariable Long id) {
        return regionService.findById(id);
    }

    @PostMapping
    public void createRegion(@RequestBody Region region) {
        regionService.insert(region);
    }

    @PutMapping("/{id}")
    public void updateRegion(@PathVariable Long id, @RequestBody Region region) {
        region.setId(id);
        regionService.update(region);
    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Long id) {
        regionService.delete(id);
    }
}
