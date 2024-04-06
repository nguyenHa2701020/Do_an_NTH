package com.doan.elearning.api;

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

import com.doan.elearning.dto.TopicDto;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.service.TopicService;

@RestController
@RequestMapping("api/topic")
public class TopicApi {
    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public ResponseEntity<List<Topic>> getAll() {
        List<Topic> listTopic = topicService.findAll();
        return ResponseEntity.ok(listTopic);
    }

    @PostMapping("/save")
    public ResponseEntity<Topic> saveClass(@ModelAttribute TopicDto topicDto) {
        Topic topic = topicService.save(topicDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> getById(@PathVariable Long id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateTopic(@ModelAttribute TopicDto topicDto) {
        topicService.update(topicDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
