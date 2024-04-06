package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.TopicDto;

import com.doan.elearning.entity.Topic;

import com.doan.elearning.repository.TopicRepository;
import com.doan.elearning.service.TopicService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicServiceImple implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public void delete(Long id) {
        topicRepository.deleteById(id);

    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(Long id) {

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
        Topic topicUpdate = topicRepository.getReferenceById(topicDto.getId());

        topicUpdate.setName(topicDto.getName());

        return topicRepository.save(topicUpdate);
    }

}
