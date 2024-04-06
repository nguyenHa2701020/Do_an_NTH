package com.doan.elearning.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.doan.elearning.dto.ScheduleDto;

import com.doan.elearning.entity.Schedule;


public interface ScheduleService {
    void save(ScheduleDto scheduleDto);

    Schedule updateNotification(Long id, boolean isNotification);

    List<Schedule> findAll();

    List<Schedule> findByIdClass(Long idlg);

    Schedule findById(Long id);

    List<Map<String, String>> dayofWeekGV(LocalDate localDate, Long id);

    List<Schedule> sheduleOfWeekGV(LocalDate localDate, Long id);
    void deleteSchedule(Long id);
}
