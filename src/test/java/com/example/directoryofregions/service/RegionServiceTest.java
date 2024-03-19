package com.example.directoryofregions.service;

import com.example.directoryofregions.dto.RegionDto;
import com.example.directoryofregions.exception.RegionExistsException;
import com.example.directoryofregions.mapper.RegionMapper;
import com.example.directoryofregions.mapper.RegionMapperDto;
import com.example.directoryofregions.entity.Region;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {

    @Mock
    private RegionMapper regionMapper;
    @Mock
    private RegionMapperDto regionMapperDto;
    @InjectMocks
    private RegionService regionService;

    @Test
    void findAllShouldReturnListOfRegionDtos() {
        // GIVEN
        Region mockRegion1 = new Region(1L, "RegionName1", "RN1");
        Region mockRegion2 = new Region(2L, "RegionName2", "RN2");
        when(regionMapper.findAll()).thenReturn(Arrays.asList(mockRegion1, mockRegion2));
        when(regionMapperDto.toDto(any(Region.class))).thenAnswer(invocation -> {
            Region region = invocation.getArgument(0);
            return new RegionDto(region.getId(), region.getName(), region.getShortName());
        });

        // WHEN
        List<RegionDto> result = regionService.findAll();

        // THEN
        assertEquals(2, result.size());
        verify(regionMapper, times(1)).findAll();
        verify(regionMapperDto, times(2)).toDto(any(Region.class));
    }

    @Test
    void findByIdShouldReturnRegionDto() {
        // GIVEN
        Region mockRegion = new Region(1L, "RegionName", "RN");
        when(regionMapper.findById(1L)).thenReturn(mockRegion);
        when(regionMapperDto.toDto(mockRegion)).thenReturn(new RegionDto(1L, "RegionName", "RN"));

        // WHEN
        RegionDto result = regionService.findById(1L);

        // THEN
        assertNotNull(result);
        assertEquals("RegionName", result.getName());
        verify(regionMapper, times(1)).findById(1L);
    }

    @Test
    void insertShouldThrowExceptionWhenRegionExists() {
        // GIVEN
        RegionDto newRegionDto = new RegionDto(1L, "NewRegion", "NR");
        when(regionMapperDto.toEntity(newRegionDto)).thenReturn(new Region(1L, "NewRegion", "NR"));
        when(regionMapper.countByNameOrShortName(eq("NewRegion"), eq("NR"))).thenReturn(1);

        // WHEN & THEN
        assertThrows(RegionExistsException.class, () -> regionService.insert(newRegionDto));

        verify(regionMapper).countByNameOrShortName("NewRegion", "NR");
    }


    @Test
    void insertShouldAddNewRegion() {
        // GIVEN
        RegionDto newRegionDto = new RegionDto(1L, "NewRegion", "NR");
        when(regionMapperDto.toEntity(newRegionDto)).thenReturn(new Region(1L, "NewRegion", "NR"));
        when(regionMapper.countByNameOrShortName("NewRegion", "NR")).thenReturn(0);

        ArgumentCaptor<Region> regionCaptor = ArgumentCaptor.forClass(Region.class);

        // WHEN
        regionService.insert(newRegionDto);

        // THEN
        verify(regionMapper).insert(regionCaptor.capture());
        Region capturedRegion = regionCaptor.getValue();
        assertEquals("NewRegion", capturedRegion.getName());
    }

    @Test
    void updateShouldUpdateExistingRegion() {
        // GIVEN
        RegionDto updatedRegionDto = new RegionDto(1L, "UpdatedName", "UN");
        when(regionMapperDto.toEntity(updatedRegionDto)).thenReturn(new Region(1L, "UpdatedName", "UN"));
        ArgumentCaptor<Region> regionCaptor = ArgumentCaptor.forClass(Region.class);

        // WHEN
        regionService.update(updatedRegionDto);

        // THEN
        verify(regionMapper).update(regionCaptor.capture());
        Region capturedRegion = regionCaptor.getValue();
        assertEquals("UpdatedName", capturedRegion.getName());
    }

    @Test
    void deleteShouldRemoveTheRegion() {
        // GIVEN
        Long idToDelete = 1L;

        // WHEN
        regionService.delete(idToDelete);

        // THEN
        verify(regionMapper).delete(idToDelete);
    }
}