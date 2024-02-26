package com.doan.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.TopicDetail;
@Repository
public interface TopicDetailRepository extends JpaRepository<TopicDetail, Long> {
    
}
