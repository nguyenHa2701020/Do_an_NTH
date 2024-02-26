package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Exam;
@Repository
public interface ExamRepository extends JpaRepository<Exam, Long>{
    
}
