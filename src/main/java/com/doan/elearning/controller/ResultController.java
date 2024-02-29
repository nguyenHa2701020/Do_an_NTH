package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Result;
import com.doan.elearning.service.ResultService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @GetMapping("/findResultByExam/{id}")
    public String findResultByExam(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage questions");
        List<Result> lstResult = resultService.findResultByExam(id);

        model.addAttribute("result", lstResult);
        model.addAttribute("size", lstResult.size());
        return "result";
    }

    @GetMapping("/result")
    public String re(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage questions");
        List<Result> lstResult = resultService.findAll();

        model.addAttribute("result", lstResult);
        model.addAttribute("size", lstResult.size());
        return "result";
    }
}
