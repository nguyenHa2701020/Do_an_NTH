package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select o from Notification o where o.schedule.id= ?1 ")
    List<Notification> findByIdSchedule(Long id);
}