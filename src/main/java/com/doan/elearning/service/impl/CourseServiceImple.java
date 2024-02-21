package com.doan.elearning.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
    public Course save(MultipartFile imageProduct, CourseDto coursedto) {
        Course cou = new Course();
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

    @Override
    public Optional<Course> findById(Long id) {
        // TODO Auto-generated method stub
        return courseRepository.findById(id);
    }

    @Override
    public Course update(MultipartFile imgCourse, CourseDto coursedto ) {

        try {
            Course courseUpdate = courseRepository.getReferenceById(coursedto.getId());
            if (imgCourse.getBytes().length > 0) {
                if (imageUpload.checkExist(imgCourse)) {
                    courseUpdate.setImage(courseUpdate.getImage());
                } else {
                    imageUpload.uploadFile(imgCourse);
                    courseUpdate.setImage(Base64.getEncoder().encodeToString(imgCourse.getBytes()));
                }
            }
         
            courseUpdate.setId(courseUpdate.getId());
            courseUpdate.setName(coursedto.getName());
       
            return courseRepository.save(courseUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Course course= courseRepository.getReferenceById(id);
       courseRepository.delete(course);
    }

    @Override
    public List<Course> findCourses(String keyword) {
        return courseRepository.findByName(keyword);
    }

}
