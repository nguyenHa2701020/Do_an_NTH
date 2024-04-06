package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("select p from Topic p")
    List<Topic> findAll();

    @Query("select o from Topic o where o.id = ?1")
    Topic findTopic(Long id);


}
