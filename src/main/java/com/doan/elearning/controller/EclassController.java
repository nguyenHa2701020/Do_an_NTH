package com.doan.elearning.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.ClassDto;
import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Level;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.LevelService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EclassController {
    private final ClassService cs;
    private final LevelService ls;
     @RequestMapping("/eclass")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Class");
        List<Eclass> ec = cs.findAll();
        List<Level> lv = ls.findAll();
        model.addAttribute("eclass", ec);
        model.addAttribute("level", lv);
        model.addAttribute("size", ec.size());
        model.addAttribute("classDto", new ClassDto());
        return "eclass";

    }

    @PostMapping("/save-eclass")
    public String addLevel(@ModelAttribute("classDto") ClassDto classDto,
            RedirectAttributes redirectAttributes) {
        try {
            cs.save( classDto);
            redirectAttributes.addFlashAttribute("success", "Add new level successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new level!");
        }
        return "redirect:/eclass";

    }
}
