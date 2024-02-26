package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.elearning.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long>{
    
}
