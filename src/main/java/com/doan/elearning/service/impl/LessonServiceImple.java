package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.LessonDto;
import com.doan.elearning.entity.Lesson;

import com.doan.elearning.repository.LessonRepository;
import com.doan.elearning.service.LessonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class LessonServiceImple implements LessonService {
    private final LessonRepository lessonRepository;

    @Override
    public Lesson save(LessonDto lesson) {
        Lesson ls = new Lesson();
        ls.setName(lesson.getName());
        ls.setLink(lesson.getLink());
        ls.setDocument(lesson.getDocument());
        ls.setUserss(lesson.getUserss());
        ls.setEclass(lesson.getEclass());

        return lessonRepository.save(ls);
    }

    @Override
    public List<Lesson> findLessonByClass(Long id) {
        return lessonRepository.findLessonByClass(id);
    }

    @Override
    public Lesson findLessons(Long id) {
        return lessonRepository.findByIdLs(id);
    }

    @Override
    public Lesson updateLessons(Lesson les) {
        return lessonRepository.save(les);
    }
}
