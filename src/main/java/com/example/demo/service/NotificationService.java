package com.example.demo.service;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.get.NotificationDtoGet;

import java.util.List;

public interface NotificationService {
    NotificationDto save(NotificationDto notificationDto);

    NotificationDto update(Long id, NotificationDto notificationDto);

    NotificationDto delete(Long id);

    List<NotificationDtoGet> getAll();

    Object search(Long id);
}
