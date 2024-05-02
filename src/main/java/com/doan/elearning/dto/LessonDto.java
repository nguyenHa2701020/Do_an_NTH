package com.doan.elearning.dto;

import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.entity.Users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDto {
    private Long id;
    private String name;
    private String document;
    private String link;
    private String note;
    private Eclass eclass;
    private Users userss;
    private Schedule schedule;

}
