package com.doan.elearning.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.doan.elearning.entity.Level;
import com.doan.elearning.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDto {
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date start;

    private Level level;
    private Long idGV;
    private Users gv;
}
