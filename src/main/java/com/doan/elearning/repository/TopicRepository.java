package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doan.elearning.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{
    @Query("select p from Topic p")
    List<Topic> findAll();

}
