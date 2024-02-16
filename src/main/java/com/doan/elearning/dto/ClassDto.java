package com.doan.elearning.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.doan.elearning.entity.Level;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDto {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+7")
    private Date start;

    private Level level;
    private Long idGV;
}
