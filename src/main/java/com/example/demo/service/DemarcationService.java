package com.example.demo.service;

import com.example.demo.dto.DemarcationDto;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface DemarcationService {
    DemarcationDto save(DemarcationDto dto);

    DemarcationDto update(DemarcationDto demarcationDto, Long id);

    List<DemarcationDto> getAll();

    DemarcationDto getById(String s);
}
