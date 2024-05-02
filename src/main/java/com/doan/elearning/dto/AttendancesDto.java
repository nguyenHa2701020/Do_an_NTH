package com.doan.elearning.dto;

import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendancesDto {
   
    private Long id;
    private Boolean status= false;
    
  
    private Users userss;
  
    private Lesson lesson;
}

