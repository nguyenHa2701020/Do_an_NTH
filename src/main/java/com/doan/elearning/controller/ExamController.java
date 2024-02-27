package com.doan.elearning.controller;

import java.io.File;
import java.security.Principal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.ExamDto;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.ExamService;
import com.doan.elearning.service.LessonService;
import com.doan.elearning.service.TopicService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    private final UserService us;
    private final ClassService cs;
    private final TopicService topicService;

    @GetMapping("/exam")
    public String exam(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByLgid(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

        }
        List<Exam> ls = examService.findAll();
        model.addAttribute("exam", ls);
        model.addAttribute("size", ls.size());
        return "exam";

    }

    @GetMapping("/addexam")
    public String addexam(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByLgid(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            ExamDto examDto = new ExamDto();
            List<Topic> topics = topicService.findAll();
            List<Eclass> eclass = cs.findAll();
            model.addAttribute("examDto", examDto);
            model.addAttribute("topic", topics);
            model.addAttribute("eclass", eclass);
        }

        return "addexam";
    }

    @PostMapping("/addexam")
    public String addexam(@ModelAttribute("examDto") ExamDto examDto,
            RedirectAttributes redirectAttributes, Principal principal) {
        try {
            // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            // examDto.setDateExam(dateFormat.parse(examDto.getDateExam().toString()));
            // examDto.setStartExam(Time.valueOf(examDto.getStartExam().toString()));
            // examDto.setEndExam(Time.valueOf(examDto.getEndExam().toString()));
            examService.save(examDto);
            redirectAttributes.addFlashAttribute("success", "Add new level successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new level!");
        }

        return "redirect:/exam";
    }

    @GetMapping("/examclass")
    public String examclass(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByLgid(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            
            List<Exam> exams = examService.findExamByClass(id);
            model.addAttribute("exams", exams);
        }

        return "examclass";
    }


}
