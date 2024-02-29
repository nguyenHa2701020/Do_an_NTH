package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.ExamSlipDto;

import com.doan.elearning.entity.ExamSlip;

import com.doan.elearning.repository.ExamSlipRepository;
import com.doan.elearning.service.ExamSlipService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamSlipServiceImple implements ExamSlipService {
    private final ExamSlipRepository examSlipRepository;

    @Override
    public List<ExamSlip> findAll() {
        return examSlipRepository.findAll();
    }

    @Override
    public List<ExamSlip> findExamSlipByUser(Long idUser, Long idTopic) {
        return examSlipRepository.findExamSlipByUser(idUser, idTopic);
    }

    @Override
    public ExamSlip save(ExamSlipDto examSlipDto) {
        ExamSlip examSlip = new ExamSlip();
        examSlip.setAnswer(examSlipDto.getAnswer());
        examSlip.setTopicDetail(examSlipDto.getTopicDetail());
        examSlip.setUsers(examSlipDto.getUsers());
        return examSlipRepository.save(examSlip);
    }

    @Override
    public ExamSlip update(String answer, Long id) {
        ExamSlip examSlipUpdate = examSlipRepository.getReferenceById(id);

        examSlipUpdate.setAnswer(answer);
        return examSlipRepository.save(examSlipUpdate);
    }

    @Override
    public ExamSlip finfExamSlip(Long id) {
      return examSlipRepository .finfExamSlip(id);
    }

}
