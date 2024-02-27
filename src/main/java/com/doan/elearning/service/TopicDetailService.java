package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.ScheduleDto;
import com.doan.elearning.dto.TopicDetailDto;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.entity.TopicDetail;

public interface TopicDetailService {
    TopicDetail save(TopicDetailDto topicDetailDto);

      List<TopicDetail> findAll();
      
      List<TopicDetail> findTopicDetailByTopic(Long idTopic); 
}
