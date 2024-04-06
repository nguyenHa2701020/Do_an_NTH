package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.LessonDto;
import com.doan.elearning.entity.Lesson;

public interface LessonService {
    Lesson save(LessonDto lesson);

    List<Lesson> findLessonByClass(Long id);

    Lesson findLessons(Long id);

    Lesson updateLessons(Lesson les);
}
