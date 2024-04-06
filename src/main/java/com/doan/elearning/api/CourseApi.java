package com.doan.elearning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.service.CourseService;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/course")
public class CourseApi {
    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAll() {
        List<Course> listCourse = courseService.findAll();
        return ResponseEntity.ok(listCourse);
    }

    @PostMapping("/save")
    public ResponseEntity<Course> postMethodName(@ModelAttribute CourseDto courseDto) {
        Course course = courseService.save(courseDto.getImageCourse(), courseDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")

    public ResponseEntity<Course> getById(@PathVariable Long id) {
        Course course = courseService.findById(id).get();
        return ResponseEntity.ok(course);

    }

    @PutMapping("/update")
    public ResponseEntity<Void> putMethodName(@ModelAttribute CourseDto courseDto) {
        courseService.update(courseDto.getImageCourse(), courseDto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMethodName(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
