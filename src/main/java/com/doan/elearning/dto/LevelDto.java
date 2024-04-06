package com.doan.elearning.dto;

import com.doan.elearning.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelDto {
    //@Size(min = 3, max = 10, message = "User name contains 3-10 characters")
    private Long id;
    private String name;

    private Course course;
}
