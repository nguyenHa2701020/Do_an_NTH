package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.TopicDto;

import com.doan.elearning.entity.Topic;

public interface TopicService {
    Topic save(TopicDto topicDto);

    Topic findById(Long id);

    Topic update(TopicDto topicDto);

    void delete(Long id);

    List<Topic> findTopic(String keyword);

    List<Topic> findAll();
}
