package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.elearning.entity.TopicDetail;

public interface TopicDetailRepository extends JpaRepository<TopicDetail, Long> {
    
}
