package com.doan.elearning.service;

import java.util.List;


import com.doan.elearning.dto.ExamSlipDto;

import com.doan.elearning.entity.ExamSlip;

public interface ExamSlipService {
    ExamSlip save(ExamSlipDto examSlipDto);

    List<ExamSlip> findExamSlipByUser(Long idUser, Long idTopic);

    List<ExamSlip> findAll();

    ExamSlip update(String answer, Long id);
    ExamSlip finfExamSlip( Long id);

}
