package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select p from Course p")
    List<Course> findAll();

    @Query(value = "update Course set name = ?1 where id=?2")
    Course update(String name, Long id);

    @Query("select p from Course p where p.name like %?1%")
    List<Course> findByName(String name);
}
