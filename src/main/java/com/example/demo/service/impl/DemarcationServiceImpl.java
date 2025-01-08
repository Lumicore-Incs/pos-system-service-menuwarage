package com.example.demo.service.impl;

import com.example.demo.dto.DemarcationDto;
import com.example.demo.model.Demarcation;
import com.example.demo.repository.DemarcationRepo;
import com.example.demo.service.DemarcationService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemarcationServiceImpl implements DemarcationService {
    private final DemarcationRepo demarcationRepo;
    private final ModelMapperConfig modelMapper;

    public DemarcationServiceImpl(DemarcationRepo demarcationRepo, ModelMapperConfig modelMapper) {
        this.demarcationRepo = demarcationRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public DemarcationDto save(DemarcationDto dto) {
        Demarcation save = demarcationRepo.save(dtoToEntity(dto));
        return entityToDto(save);
    }

    @Override
    public DemarcationDto update(DemarcationDto demarcationDto, Long id) {
        Optional<Demarcation> byId = demarcationRepo.findById(id);
        if (byId.isPresent()) {
            demarcationDto.setId(id);
            Demarcation update = demarcationRepo.save(dtoToEntity(demarcationDto));
            return entityToDto(update);
        }else {
            return null;
        }
    }

    @Override
    public List<DemarcationDto> getAll() {
        List<Demarcation> all = demarcationRepo.findAll();
        List<DemarcationDto> dtoArrayList = new ArrayList<>();
        for (Demarcation d : all) {
            dtoArrayList.add(entityToDto(d));
        }
        return dtoArrayList;
    }

    @Override
    public Object getById(String id) {
        Optional<Demarcation> byId = demarcationRepo.findById(Long.parseLong(id));
        if (byId.isPresent()) {
            return entityToDto(byId.get());
        }return "[]";
    }

    private Demarcation dtoToEntity(DemarcationDto dto) {
        return modelMapper.modelMapper().map(dto, Demarcation.class);
    }

    private DemarcationDto entityToDto(Demarcation demarcation) {
        return (demarcation == null) ? null : modelMapper.modelMapper().map(demarcation, DemarcationDto.class);
    }
}
