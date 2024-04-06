package com.doan.elearning.service;


import java.util.List;
import java.util.Optional;

import com.doan.elearning.entity.Notification;


public interface NotificationService {
    Notification save(Notification notification);

    List<Notification> findByIdSchedule(Long id);

    Optional<Notification> findById(Long id);

}
