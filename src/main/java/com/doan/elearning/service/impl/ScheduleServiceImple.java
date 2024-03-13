package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.ScheduleDto;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.repository.ShceduleRepository;
import com.doan.elearning.service.ScheduleService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ScheduleServiceImple implements ScheduleService {
private final ShceduleRepository sr;
    @Override
    public List<Schedule> findAll() {
       return sr.findAll();
    }

    @Override
    public Schedule save(ScheduleDto scheduleDto) {
       Schedule sche= new Schedule();
       sche.setStudytime(scheduleDto.getStudytime());
       sche.setDatelearn(scheduleDto.getDatelearn());
       sche.setEclass(scheduleDto.getEclass());
    return sr.save(sche);
}

   @Override
   public List<Schedule> findByIdClass(Long idlg) {
      
      return sr.findByIdClass(idlg);
   }
}
