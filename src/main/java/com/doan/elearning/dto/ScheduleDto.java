package com.doan.elearning.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.doan.elearning.entity.Eclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {
    private String studytime;

    private String numberdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")

    private Date datelearn;

    private Long idclass;
    private Eclass eclass;
private int numberLesson;
}
