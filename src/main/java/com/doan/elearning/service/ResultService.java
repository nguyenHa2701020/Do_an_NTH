package com.doan.elearning.service;

import java.io.IOException;
import java.util.List;

import com.doan.elearning.dto.ResultDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Result;

import jakarta.servlet.http.HttpServletResponse;

public interface ResultService {
    Result save(ResultDto resultDto);

    Result findResultByUser(Long idUser, Long idExam);

    List<Result> findAll();

    Result update(Result result);

    List<Result> findResultByExam(Long idExam);

    void exportToExcel(List<Result> objectList, HttpServletResponse response) throws IOException;

    Result findResult(Long idResult);
}
