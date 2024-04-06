package com.doan.elearning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.ClassDto;
import com.doan.elearning.dto.ScheduleDto;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleApi {
@Autowired
private ScheduleService scheduleService;
@GetMapping("/")
public ResponseEntity<List<Schedule>> getAll()
{
    List<Schedule> lstSchedule= scheduleService.findAll();
    return ResponseEntity.ok(lstSchedule);
    
}
  @PostMapping("/save")
    public ResponseEntity<Void> saveSchedule(@ModelAttribute ScheduleDto scheduleDto) {
        scheduleService.save(scheduleDto);
        return ResponseEntity.ok().build();
    }
@GetMapping("/{id}")
    public ResponseEntity<Schedule> getById(@PathVariable Long id)
    {
        Schedule schedule= scheduleService.findById(id);
        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deleteSchedule(@PathVariable Long id)
{
    scheduleService.deleteSchedule(id);
    return ResponseEntity.ok().build();
}
}
