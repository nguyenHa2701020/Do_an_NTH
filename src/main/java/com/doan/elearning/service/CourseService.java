package com.doan.elearning.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;

import jakarta.servlet.http.HttpServletResponse;

public interface CourseService {
    Course save(MultipartFile imageProduct, CourseDto coursedto);

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course update(MultipartFile imgCourse, CourseDto coursedto);

    void delete(Long id);

    List<Course> findCourses(String keyword);

    void exportToExcel(List<Course> objectList, HttpServletResponse response) throws IOException;


}
