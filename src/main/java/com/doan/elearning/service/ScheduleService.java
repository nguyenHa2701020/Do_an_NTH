package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ScheduleDto;

import com.doan.elearning.entity.Schedule;

public interface ScheduleService {
      Schedule save(ScheduleDto scheduleDto);

      List<Schedule> findAll();

      List<Schedule> findByLgid(Long idlg);
}
