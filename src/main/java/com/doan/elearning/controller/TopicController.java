package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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


        topicService.save(Topic);
        redirectAttributes.addFlashAttribute("success", "Add new Topic successfully!");

        return "redirect:/topic";

    }

    @RequestMapping(value = "/delete-topic", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            topicService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }

        return "redirect:/topic";
    }

    @GetMapping("/update-topic/{id}")
    public String updateTopic(@PathVariable("id") Long id, Model model) {


        Topic topic = topicService.findById(id);

        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setName(topic.getName());

        model.addAttribute("title", "Add Topic");
        model.addAttribute("topicDto", topicDto);
        return "Admin/update-topic";
    }

    @PostMapping("/update-topic/{id}")
    public String updateProduct(@ModelAttribute("topicDto") TopicDto topicDto,
                                RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            topicService.update(topicDto);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/topic";
    }


}
