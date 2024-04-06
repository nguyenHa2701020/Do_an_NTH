package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.TopicDetailDto;
import com.doan.elearning.entity.Questions;
import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.repository.TopicDetailRepository;
import com.doan.elearning.service.TopicDetailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicDetailServiceImple implements TopicDetailService {

    private final TopicDetailRepository topicDetailRepository;

    @Override
    public List<TopicDetail> findAll() {
        // TODO Auto-generated method stub
        return topicDetailRepository.findAll();
    }

    @Override
    public TopicDetail save(TopicDetailDto topicDetailDto) {
        // TODO Auto-generated method stub
        TopicDetail tp = new TopicDetail();
        tp.setQuestions(topicDetailDto.getQuestions());
        tp.setTopic(topicDetailDto.getTopic());
        return topicDetailRepository.save(tp);
    }

    @Override
    public List<TopicDetail> findTopicDetailByTopic(Long idTopic) {
        return topicDetailRepository.findTopicDetailByTopic(idTopic);
    }

    @Override
    public void delete(Long id) {


        topicDetailRepository.deleteById(id);

    }


}
