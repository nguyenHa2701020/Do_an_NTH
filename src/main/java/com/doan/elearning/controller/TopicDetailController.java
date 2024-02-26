package com.doan.elearning.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.TopicDetailDto;
import com.doan.elearning.dto.TopicDto;
import com.doan.elearning.entity.Questions;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.service.QuestionService;
import com.doan.elearning.service.TopicDetailService;
import com.doan.elearning.service.TopicService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TopicDetailController {
    private final QuestionService quesionService;
    private final TopicDetailService topicDetailService;
    private final TopicService topicService;
    
    @RequestMapping("/topicdetail")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Topic");
        List<TopicDetail> TopicsDetail = topicDetailService.findAll();
        model.addAttribute("TopicsDetail", TopicsDetail);
        model.addAttribute("size", TopicsDetail.size());
        List<Topic> Topics = topicService.findAll();
         model.addAttribute("topic", Topics);
        model.addAttribute("TopicDto", new TopicDetailDto());
        return "topicdetail";

    }

    @PostMapping("/save-topicdetail")
    public String addTopic(@ModelAttribute("TopicDto") TopicDetailDto Topic,
            RedirectAttributes redirectAttributes) {

        List<Questions> lstread=quesionService.randomQuestion("Reading", Topic.getNumRead());
        List<Questions> lstLis=quesionService.randomQuestion("Listening", Topic.getNumListen());
        List<Questions> lstwwr=quesionService.randomQuestion("Writing", Topic.getNumWrite());
        List<Questions> lstQues=new ArrayList<>();
        lstQues.addAll(lstread);
        lstQues.addAll(lstLis);
        lstQues.addAll(lstwwr);
        for (Questions questions : lstQues) {
            Topic.setQuestions(questions);
            topicDetailService.save(Topic);
        }
        
       // quesionService.save(Topic);
        redirectAttributes.addFlashAttribute("success", "Add new Topic successfully!");

        return "redirect:/topicdetail";

    }
}
