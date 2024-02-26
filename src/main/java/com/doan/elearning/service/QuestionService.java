package com.doan.elearning.service;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.doan.elearning.dto.QuestionDto;
import com.doan.elearning.entity.Questions;

public interface QuestionService {
    Questions save(QuestionDto questionDto);

    List<Questions> findAll();

    void delete(Long id);

    List<Questions> randomQuestion(String type, int number );
}
