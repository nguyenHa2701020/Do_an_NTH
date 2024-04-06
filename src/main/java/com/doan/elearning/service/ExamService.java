package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ExamDto;

import com.doan.elearning.entity.Exam;


public interface ExamService {
    Exam save(ExamDto examDto);

    List<Exam> findExamByClass(Long id);

    Exam update(Exam exam);

    List<Exam> findAll();

    Exam findExam(Long id);

    Exam updateExam(boolean status, Long id);

}
