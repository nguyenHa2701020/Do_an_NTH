package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.doan.elearning.entity.TopicDetail;

@Repository
public interface TopicDetailRepository extends JpaRepository<TopicDetail, Long> {
    @Query("select o from TopicDetail o where o.topic.id= ?1 ")
    List<TopicDetail> findTopicDetailByTopic(Long idTopic);

}
