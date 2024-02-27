package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doan.elearning.dto.ExamSlipDto;
import com.doan.elearning.dto.ResultDto;
import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.ExamSlip;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.ExamService;
import com.doan.elearning.service.ExamSlipService;
import com.doan.elearning.service.ResultService;
import com.doan.elearning.service.TopicDetailService;
import com.doan.elearning.service.TopicService;
import com.doan.elearning.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ExamSlipController {

    private final ExamSlipService examSlipService;
    private final UserService us;
    private final ExamService examService;
    private final TopicService topicService;
    private final TopicDetailService topicDetailService;
    private final ResultService resultService;
    @GetMapping("/studentExam")
    public String examSlip(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByLgid(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            Exam exam = examService.findExam(id);
            List<ExamSlip> examSlips= examSlipService.findExamSlipByUser(usk.getId(), exam.getIdTopic());
            if(examSlips.isEmpty())
            {
                Topic topic = topicService.findById(exam.getIdTopic());
                List<TopicDetail> topicDetail = topicDetailService.findTopicDetailByTopic(topic.getId());
                ExamSlipDto examSlipDto= new ExamSlipDto();
                examSlipDto.setUsers(usk);
                for (TopicDetail topicDetail2 : topicDetail) {
                    examSlipDto.setTopicDetail(topicDetail2);
                    examSlipService.save(examSlipDto);
                }

                ResultDto resultDto= new ResultDto();
                resultDto.setExam(exam);
                resultDto.setUsers(usk);
                resultDto.setListenPoint(0f);
                resultDto.setReadPoint(0f);
                resultDto.setSpeakPoint(0f);
                resultDto.setWritePoint(0f);

                resultService.save(resultDto);

            }
            List<ExamSlip> examSlipsYes= examSlipService.findExamSlipByUser(usk.getId(), exam.getIdTopic());
            model.addAttribute("examSlip", examSlipsYes);
         
        }
        // List<ExamSlip> ls = examSlipService.findAll();
        // model.addAttribute("examSlip", ls);
        // model.addAttribute("size", ls.size());
        return "studentExam";

    }

     @PostMapping("/updateExamSlip")
    public String updateExamSlip(@RequestParam("answer") String answer,
                              @RequestParam("id") Long id,HttpServletRequest request) {
        examSlipService.update(answer, id);
        return "redirect:" + request.getHeader("Referer");
    }
}
