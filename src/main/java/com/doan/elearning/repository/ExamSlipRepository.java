package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.ExamSlip;
@Repository
public interface ExamSlipRepository extends JpaRepository<ExamSlip, Long>{
    
}
