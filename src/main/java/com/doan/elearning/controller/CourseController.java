package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseService us;

    @RequestMapping("/course")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Course");
        List<Course> courses = us.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("size", courses.size());
        model.addAttribute("usernew", new Users());
        model.addAttribute("courseDto", new CourseDto());
        return "course";

    }

    @PostMapping("/save-course")
    public String addCourse(@ModelAttribute("courseDto") CourseDto course,
            @RequestParam("imageProduct") MultipartFile imageProduct,
            RedirectAttributes redirectAttributes) {
        try {

            us.save(imageProduct, course);
            redirectAttributes.addFlashAttribute("success", "Add new course successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new course!");
        }
        return "redirect:/course";

    }

}
