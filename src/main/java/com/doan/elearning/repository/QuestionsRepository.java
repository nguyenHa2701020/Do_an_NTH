package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.elearning.entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long>{
    
}
