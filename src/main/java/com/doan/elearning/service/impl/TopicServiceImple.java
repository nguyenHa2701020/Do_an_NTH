package com.doan.elearning.service.impl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.TopicDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.repository.CourseRepository;
import com.doan.elearning.repository.TopicRepository;
import com.doan.elearning.service.TopicService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TopicServiceImple implements TopicService{
 private final TopicRepository topicRepository;
    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Topic> findAll() {
       return topicRepository.findAll();
    }

    @Override
    public Topic findById(Long id) {
        // TODO Auto-generated method stub
        return topicRepository.findTopic(id);
    }

    @Override
    public List<Topic> findTopic(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Topic save(TopicDto topicDto) {
        Topic topic = new Topic();
    
        topic.setName(topicDto.getName());

            return topicRepository.save(topic);
    
    }

    @Override
    public Topic update(TopicDto topicDto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
