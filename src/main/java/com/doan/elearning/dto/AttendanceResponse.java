package com.doan.elearning.dto;

import java.util.List;
import java.util.Map;

import com.doan.elearning.entity.Attendances;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {
    //title hiển thị tên buổi học
    private List<String> title;
    //Map hiển thị danh sách gồm tên ng dùng gồm 1 ds điểm danh 
    private List<Map<String,List<Attendances>>> content;
}
