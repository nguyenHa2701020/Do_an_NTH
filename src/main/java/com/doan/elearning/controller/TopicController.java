package com.doan.elearning.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.TopicDto;
import com.doan.elearning.entity.Topic;

import com.doan.elearning.service.TopicService;


import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class TopicController {
     private final TopicService topicService;

    @RequestMapping("/topic")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Topic");
        List<Topic> Topics = topicService.findAll();
        model.addAttribute("Topics", Topics);
        model.addAttribute("size", Topics.size());
        //model.addAttribute("usernew", new Users());
        model.addAttribute("TopicDto", new TopicDto());
        return "Admin/topic";

    }

    @PostMapping("/save-topic")
    public String addTopic(@ModelAttribute("TopicDto") TopicDto Topic,
            RedirectAttributes redirectAttributes) {
       

            topicService.save( Topic);
            redirectAttributes.addFlashAttribute("success", "Add new Topic successfully!");
     
        return "redirect:/topic";

    }

    
}
