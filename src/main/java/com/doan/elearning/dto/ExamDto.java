package com.doan.elearning.dto;


import com.doan.elearning.entity.Eclass;


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
    private boolean status;
    private Eclass eclass;
    private Long idTopic;
}
