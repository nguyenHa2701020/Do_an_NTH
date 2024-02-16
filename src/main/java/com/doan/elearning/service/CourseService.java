package com.doan.elearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;

public interface CourseService {
    Course save(MultipartFile imageProduct, CourseDto coursedto);

    List<Course> findAll();

    Optional<Course> findById(Long id);

    Course update(Course course);
    void delete(Long id);
     List<Course> findCourses(String keyword);


}
