package com.doan.elearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.elearning.entity.Attendances;
import com.doan.elearning.repository.AttendancesRepository;

import com.doan.elearning.service.AttendancesService;

@Service
// @RequiredArgsConstructor
public class AttendanceServiceImple implements AttendancesService {
    @Autowired
    private AttendancesRepository attendanceRepository;

    @Override
    public Attendances findByUserAndLesson(Long idUser, Long idLesson) {
        return attendanceRepository.findByUserAndLesson(idUser, idLesson);
    }

    @Override
    public Attendances updateAtten(Long id) {
        Attendances attendances = attendanceRepository.findById(id).get();
        if (attendances.getStatus() == true) {
            attendances.setStatus(false);

        } else {
            attendances.setStatus(true);
        }
        return attendanceRepository.save(attendances);
    }
}