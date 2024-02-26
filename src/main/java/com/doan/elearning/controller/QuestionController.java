package com.doan.elearning.controller;

import java.util.List;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.dto.QuestionDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Level;
import com.doan.elearning.entity.Questions;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.service.LevelService;
import com.doan.elearning.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QuestionController {
     private final QuestionService questionService;
    private final LevelService lv;

    @RequestMapping("/questions")
    public String lst(Model model) {
        model.addAttribute("title", "Manage questions");
        List<Questions> questions = questionService.findAll();
        List<Level> level = lv.findAll();
        model.addAttribute("level", level);
        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());
        model.addAttribute("questionDto", new QuestionDto());
        return "questions";

    }

    @PostMapping("/save-questions")
    public String addQuestion(@ModelAttribute("questionDto") QuestionDto questionDto,
            RedirectAttributes redirectAttributes) {
        try {

            questionService.save( questionDto);
            redirectAttributes.addFlashAttribute("success", "Add new question successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new question!");
        }
        return "redirect:/questions";

    }
}
