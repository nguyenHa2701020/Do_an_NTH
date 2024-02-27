package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ExamDto;
import com.doan.elearning.dto.LessonDto;
import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.Lesson;

public interface ExamService {
     Exam save(ExamDto examDto);
    List<Exam> findExamByClass(Long id);

    List<Exam> findAll();
    Exam findExam(Long id); 
}
