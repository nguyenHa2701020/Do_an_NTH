package com.doan.elearning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.service.TopicDetailService;

@RestController
@RequestMapping("/api/topicdetail")
public class TopicDetailApi {
    @Autowired
    private TopicDetailService topicDetailService;
    @GetMapping("/")
    public ResponseEntity<List<TopicDetail>> getAll()
    {
        List<TopicDetail> lstTopicDetails= topicDetailService.findAll();
        return ResponseEntity.ok(lstTopicDetails);
        
    }
}
