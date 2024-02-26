package com.doan.elearning.service.impl;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;

import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.dto.QuestionDto;
import com.doan.elearning.entity.Level;
import com.doan.elearning.entity.Questions;
import com.doan.elearning.repository.LevelRepository;
import com.doan.elearning.repository.QuestionsRepository;
import com.doan.elearning.service.LevelService;
import com.doan.elearning.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImple implements QuestionService {
    private final QuestionsRepository questionsRepo;

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Questions> findAll() {
        return questionsRepo.findAll();
    }

    @Override
    public Questions save(QuestionDto questionDto) {
        Questions questions = new Questions();
        questions.setType(questionDto.getType());
        questions.setQuestion(questionDto.getQuestion());
        questions.setOption1(questionDto.getOption1());
        questions.setOption2(questionDto.getOption2());
        questions.setOption3(questionDto.getOption3());
        questions.setOption4(questionDto.getOption4());
        questions.setAnswer(questionDto.getAnswer());
        questions.setLevel(questionDto.getLevel());
        return questionsRepo.save(questions);

    }

    @Override
    public List<Questions> randomQuestion(String type, int number) {
        return questionsRepo.randomQuestion(type, number);
    }

}
