package com.doan.elearning.dto;

import java.sql.Time;
import java.util.Date;

import com.doan.elearning.entity.Eclass;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDto {
    private String name;
    private String startExam;
    private String endExam;
    private String dateExam;
    private String link;
    private Eclass eclass;
    private Long idTopic;
}
