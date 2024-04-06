package com.doan.elearning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doan.elearning.entity.Notification;
import com.doan.elearning.repository.NotificationRepository;
import com.doan.elearning.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImple implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> findByIdSchedule(Long id) {
        return notificationRepository.findByIdSchedule(id);
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

}
