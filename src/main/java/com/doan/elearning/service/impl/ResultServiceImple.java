package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.ResultDto;

import com.doan.elearning.entity.Result;

import com.doan.elearning.repository.ResultRepository;
import com.doan.elearning.service.ResultService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultServiceImple implements ResultService {
    private final ResultRepository resultRepository;

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public Result findResultByUser(Long idUser, Long idExam) {
        return resultRepository.findResultByUser(idUser, idExam);
    }

    @Override
    public Result save(ResultDto resultDto) {
        Result result = new Result();
        result.setListenPoint(resultDto.getListenPoint());
        result.setReadPoint(resultDto.getReadPoint());
        result.setSpeakPoint(resultDto.getSpeakPoint());
        result.setUserss(resultDto.getUsers());
        result.setWritePoint(resultDto.getWritePoint());
        result.setExam(resultDto.getExam());

        return resultRepository.save(result);
    }

    @Override
    public Result update(Result result) {
        Result resultUpdate = resultRepository.getReferenceById(result.getId());
        resultUpdate.setListenPoint(result.getListenPoint());
        resultUpdate.setWritePoint(result.getWritePoint());
        resultUpdate.setSubmisTime(result.getSubmisTime());
        resultUpdate.setReadPoint(result.getReadPoint());
        resultUpdate.setSpeakPoint(result.getSpeakPoint());
        resultUpdate.setUserss(result.getUserss());
        resultUpdate.setExam(result.getExam());

        return resultRepository.save(resultUpdate);
    }

    @Override
    public List<Result> findResultByExam(Long idExam) {
        return resultRepository.findResultByExam(idExam);
    }

    @Override
    public Result findResult(Long idResult) {
        return resultRepository.findResult(idResult);
    }

}
