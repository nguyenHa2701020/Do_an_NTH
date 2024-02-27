package com.doan.elearning.service;

import java.util.List;
import java.util.Optional;

import com.doan.elearning.dto.TopicDto;

import com.doan.elearning.entity.Topic;

public interface TopicService {
    Topic save(TopicDto topicDto);

    List<Topic> findAll();

   Topic findById(Long id);

    Topic update(TopicDto topicDto);

    void delete(Long id);

    List<Topic> findTopic(String keyword);

}
