package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Result;
@Repository
public interface ResultRepository extends JpaRepository<Result, Long>{
    
}
