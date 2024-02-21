package com.doan.elearning.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    // @Size(min = 3, max = 10, message = "User name contains 3-10 characters")
    private Long id;
    private String name;

    private String image;
}
