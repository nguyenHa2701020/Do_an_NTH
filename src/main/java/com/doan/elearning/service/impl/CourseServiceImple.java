package com.doan.elearning.service.impl;

import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.repository.CourseRepository;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.utils.ImageUpload;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CourseServiceImple implements CourseService {
private final CourseRepository courseRepository;
private final ImageUpload imageUpload;
    @Override
    public List<Course> findAll() {
        // TODO Auto-generated method stub
        return courseRepository.findAll();
    }

    @Override
    public Course save(MultipartFile imageProduct,CourseDto coursedto) {
        Course cou= new Course();
        try {
            if (imageProduct == null) {
                cou.setImage(null);
            } else {
                imageUpload.uploadFile(imageProduct);
                cou.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            cou.setName(coursedto.getName());
            
            return courseRepository.save(cou);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
