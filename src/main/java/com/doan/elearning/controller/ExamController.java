package com.doan.elearning.controller;


import java.security.Principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.ExamDto;

import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Exam;

import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.ExamService;

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
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

        }
        List<Exam> ls = examService.findAll();
        model.addAttribute("exam", ls);
        model.addAttribute("size", ls.size());
        return "Admin/exam";

    }

    @GetMapping("/addexam")
    public String addexam(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
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

        return "Admin/addexam";
    }

    @PostMapping("/addexam")
    public String addexam(@ModelAttribute("examDto") ExamDto examDto,
                          RedirectAttributes redirectAttributes, Principal principal) {
        try {

            examService.save(examDto);
            redirectAttributes.addFlashAttribute("success", "Add new exam successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new exam!");
        }

        return "redirect:/exam";
    }

    @GetMapping("/examclass")
    public String examclass(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

            List<Exam> exams = examService.findExamByClass(id);
            for (Exam exam : exams) {
                boolean check = status(exam.getDateExam(), exam.getStartExam(), exam.getEndExam());
                exam.setStatus(check);
                examService.update(exam);

            }
            List<Exam> examss = examService.findExamByClass(id);
            model.addAttribute("exams", examss);
        }

        return "Student/examclass";
    }

    // @RequestMapping("/status")
    // @ResponseBody
    public boolean status(String dateExam, String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        Date startTimee = new Date();
        Date endTimee = new Date();
        try {

            startTimee = dateFormat.parse(dateExam + " " + startTime + ":00");
            endTimee = dateFormat.parse(dateExam + " " + endTime + ":00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar current = Calendar.getInstance();
        current.setTime(currentTime);
        Calendar start = Calendar.getInstance();
        start.setTime(startTimee);
        Calendar end = Calendar.getInstance();
        end.setTime(endTimee);
        boolean check = (current.after(start) || current.equals(start)) && (current.before(end) || current.equals(end));
        return check;
    }

    @RequestMapping(value = "/findExamId", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Exam findExamId(Long id) {
        return examService.findExam(id);
    }
}
