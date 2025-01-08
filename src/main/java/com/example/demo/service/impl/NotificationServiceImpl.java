package com.example.demo.service.impl;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.get.NotificationDtoGet;
import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepo;
import com.example.demo.service.NotificationService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final ModelMapperConfig modelMapperConfig;
    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationServiceImpl(ModelMapperConfig modelMapperConfig, NotificationRepo notificationRepo) {
        this.modelMapperConfig = modelMapperConfig;
        this.notificationRepo = notificationRepo;
    }

    @Override
    public NotificationDto save(NotificationDto notificationDto) {
        Notification notification = dtoToEntity(notificationDto);
        Notification save = notificationRepo.save(notification);
        return entityToDto(save);
    }

    @Override
    public NotificationDto update(Long id, NotificationDto notificationDto) {
        Optional<Notification> byId = notificationRepo.findById(id);
        if (byId.isPresent()) {
            notificationDto.setId(id);
            Notification save = notificationRepo.save(dtoToEntity(notificationDto));
            return entityToDto(save);
        }
        return null;
    }

    @Override
    public NotificationDto delete(Long id) {
        Optional<Notification> byId = notificationRepo.findById(id);
        if (byId.isPresent()) {
            notificationRepo.deleteById(id);
            return entityToDto(byId.get());
        }
        return null;
    }

    @Override
    public List<NotificationDtoGet> getAll() {
        List<Notification> all = notificationRepo.findAll();
        List<NotificationDtoGet> dtos = new ArrayList<>();
        for (Notification notification : all) {
            dtos.add(entityToGetDto(notification));
        }
        return dtos;
    }

    @Override
    public Object search(Long id) {
        Optional<Notification> byId = notificationRepo.findById(id);
        if (byId.isPresent()) {
            return entityToGetDto(byId.get());
        }return "[]";
    }

    private Notification dtoToEntity(NotificationDto dto) {
        return modelMapperConfig.modelMapper().map(dto, Notification.class);
    }

    private NotificationDto entityToDto(Notification notification) {
        return (notification == null) ? null : modelMapperConfig.modelMapper().map(notification, NotificationDto.class);
    }

    private NotificationDtoGet entityToGetDto(Notification notification) {
        return (notification == null) ? null : modelMapperConfig.modelMapper().map(notification, NotificationDtoGet.class);
    }
}
