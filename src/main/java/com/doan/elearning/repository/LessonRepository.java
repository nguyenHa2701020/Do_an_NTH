package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Lesson;


@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select o from Lesson o where o.eclass.id = ?1")
    List<Lesson> findLessonByClass(Long id);

    @Query("select o from Lesson o where o.id = ?1")
    Lesson findByIdLs(Long id);
}
