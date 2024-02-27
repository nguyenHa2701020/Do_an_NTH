package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Exam;
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{
    @Query("select o from Exam o where o.eclass.id = ?1")
    List<Exam> findExamByClass(Long id);
    @Query("select o from Exam o ")
    List<Exam> findAll();
    
}
