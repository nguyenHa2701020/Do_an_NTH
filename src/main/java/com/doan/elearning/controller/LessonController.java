package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.LessonService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final UserService us;

    @GetMapping("/lesson")
    public String lesson(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByLgid(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

        }
        List<Lesson> ls = lessonService.findLessonByClass(id);
        model.addAttribute("lessons", ls);
        model.addAttribute("size", ls.size());
        return "lesson";

    }

    

    @GetMapping("/addlesson")
    public String addlesson(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByLgid(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            Lesson lesson= lessonService.findLessons(id);
            model.addAttribute("lesson", lesson);
            

        }


        return "addlesson";
    }

}
