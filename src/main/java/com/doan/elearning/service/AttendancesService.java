package com.doan.elearning.service;

import com.doan.elearning.entity.Attendances;

public interface AttendancesService {


    Attendances findByUserAndLesson(Long idUser, Long idLesson);
    Attendances updateAtten(Long id);
}
