package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ExamSlipDto;
import com.doan.elearning.dto.ResultDto;
import com.doan.elearning.entity.ExamSlip;
import com.doan.elearning.entity.Result;

public interface ResultService {
      Result save(ResultDto resultDto);
    List<Result> findResultByUser(Long idUser,Long idExam );
    List<Result> findAll();
    Result update(Result result);
}
