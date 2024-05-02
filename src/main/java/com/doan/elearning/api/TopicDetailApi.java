package com.doan.elearning.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.TopicDetailDto;
import com.doan.elearning.dto.TopicDto;
import com.doan.elearning.entity.Questions;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.service.QuestionService;
import com.doan.elearning.service.TopicDetailService;

@RestController
@RequestMapping("/api/topicdetail")
public class TopicDetailApi {
    @Autowired
    private TopicDetailService topicDetailService;
    @Autowired
    private QuestionService questionService;
    
    @GetMapping("/")
    public ResponseEntity<List<TopicDetail>> getAll()
    {
        List<TopicDetail> lstTopicDetails= topicDetailService.findAll();
        return ResponseEntity.ok(lstTopicDetails);
        
    }
    @PostMapping("/save")
    public ResponseEntity<TopicDetail> saveClass(@ModelAttribute TopicDetailDto topicDetailDto) {
            List<Questions> lstread = questionService.randomQuestion("Reading", topicDetailDto.getNumRead());
        List<Questions> lstLis = questionService.randomQuestion("Listening", topicDetailDto.getNumListen());
        List<Questions> lstwwr = questionService.randomQuestion("Writing", topicDetailDto.getNumWrite());
        List<Questions> lstQues = new ArrayList<>();
        lstQues.addAll(lstread);
        lstQues.addAll(lstLis);
        lstQues.addAll(lstwwr);
        for (Questions questions : lstQues) {
            topicDetailDto.setQuestions(questions);
            topicDetailService.save(topicDetailDto);
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTopicDetail(@PathVariable Long id) {
        topicDetailService.delete(id);
        return ResponseEntity.ok().build();
    }
}
