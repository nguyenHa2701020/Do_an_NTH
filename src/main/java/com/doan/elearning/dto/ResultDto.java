package com.doan.elearning.dto;

import java.util.Date;

import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.Users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {
    private Float listenPoint;
    private Float speakPoint;
    private Float readPoint;
    private Float writePoint;
    private Date submisTime;
    private Exam exam;

    private Users users;

}
