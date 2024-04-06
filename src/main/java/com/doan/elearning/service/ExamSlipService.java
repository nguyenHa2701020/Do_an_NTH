package com.doan.elearning.service;

import java.util.List;


import com.doan.elearning.dto.ExamSlipDto;
import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.ExamSlip;
import com.doan.elearning.entity.Users;

public interface ExamSlipService {
    ExamSlip save(ExamSlipDto examSlipDto);

    List<ExamSlip> findExamSlipByUser(Long idUser, Long idTopic);

    boolean checkTime(String dateExam, String endTime);

    List<ExamSlip> findAll();

    ExamSlip update(String answer, Long id);

    ExamSlip finfExamSlip(Long id);

    void createExamSlip(Users user, Exam exam);
}