package com.doan.elearning.controller;

import java.security.Principal;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.ExamSlipDto;
import com.doan.elearning.dto.ResultDto;
import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.ExamSlip;
import com.doan.elearning.entity.Result;
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
    public String examSlip(Long id, Model model, Principal principal, Authentication authentication,
                           RedirectAttributes redirectAttributes) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            Exam exam = examService.findExam(id);

            List<ExamSlip> examSlips = examSlipService.findExamSlipByUser(usk.getId(), exam.getIdTopic());
            if (examSlips.isEmpty()) {
                Topic topic = topicService.findById(exam.getIdTopic());
                List<TopicDetail> topicDetail = topicDetailService.findTopicDetailByTopic(topic.getId());
                ExamSlipDto examSlipDto = new ExamSlipDto();
                examSlipDto.setUsers(usk);
                for (TopicDetail topicDetail2 : topicDetail) {
                    examSlipDto.setTopicDetail(topicDetail2);
                    examSlipService.save(examSlipDto);
                }

                ResultDto resultDto = new ResultDto();
                resultDto.setExam(exam);
                resultDto.setUsers(usk);
                resultDto.setListenPoint(0f);
                resultDto.setReadPoint(0f);
                resultDto.setSpeakPoint(0f);
                resultDto.setWritePoint(0f);

                resultService.save(resultDto);
                //examSlipService.createExamSlip(usk, exam);
            }
            List<ExamSlip> examSlipsYes = examSlipService.findExamSlipByUser(usk.getId(), exam.getIdTopic());
            model.addAttribute("examSlip", examSlipsYes);

            String dateString = exam.getEndExam();
            String[] timepart = dateString.split(":");
            int hour = Integer.parseInt(timepart[0]);
            int minute = Integer.parseInt(timepart[1]);

            model.addAttribute("h", hour);
            model.addAttribute("mn", minute);
            model.addAttribute("exam", exam);
            Boolean checkTime = examSlipService.checkTime(exam.getDateExam(), exam.getEndExam());

            if (checkTime == false) {
                redirectAttributes.addFlashAttribute("error", "Exam time is over!");
                return "redirect:/examclass?id=" + exam.getEclass().getId();
            } else {
                Result result = resultService.findResultByUser(usk.getId(), exam.getId());
                if (result.getSubmisTime() != null) {
                    redirectAttributes.addFlashAttribute("error", "You have submitted your assignment!");
                    return "redirect:/examclass?id=" + exam.getEclass().getId();
                }
            }
            // model.addAttribute("idTopic", exam.getIdTopic());

        }

        return "Student/studentExam";

    }

    @PostMapping("/updateExamSlip")
    public String updateExamSlip(@RequestParam("answer") String answer,
                                 @RequestParam("id") Long id, @RequestParam("examId") Long examId, HttpServletRequest request,
                                 Principal principal) {
        examSlipService.update(answer, id);
        Users usk = us.findByUsername(principal.getName());
        // ExamSlip examSlip = examSlipService.finfExamSlip(id);
        Result result = updatResult(examId, usk);
        resultService.update(result);
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping("/submitExamSlip")
    public String submitExamSlip(
            @RequestParam("examId") Long examId, HttpServletRequest request,
            Principal principal, RedirectAttributes redirectAttributes) {

        Users usk = us.findByUsername(principal.getName());
        Result result = updatResult(examId, usk);
        result.setSubmisTime(new Date());
        resultService.update(result);
        Exam exam = examService.findExam(examId);

        redirectAttributes.addFlashAttribute("success", "You have successfully submitted your assignment!");
        return "redirect:/examclass?id=" + exam.getEclass().getId();
    }

    public Result updatResult(Long examId, Users users) {
        Exam exam = examService.findExam(examId);

        Result result = resultService.findResultByUser(users.getId(), exam.getId());
        Result result2 = scoreCalculate(users.getId(), exam.getIdTopic());
        result.setListenPoint(result2.getListenPoint());
        result.setReadPoint(result2.getReadPoint());
        // result.setSpeakPoint(0f);
        result.setWritePoint(0f);
        result.setUserss(users);
        result.setExam(exam);

        return result;
    }

    public Result scoreCalculate(Long idUser, Long idTopic) {
        List<ExamSlip> lstExamSlips = examSlipService.findExamSlipByUser(idUser, idTopic);
        int numberListenCorrect = 0;
        int numberReadCorrect = 0;
        for (ExamSlip examSlip : lstExamSlips) {
            if (examSlip.getAnswer() != null) {
                if (examSlip.getAnswer().equals(examSlip.getTopicDetail().getQuestions().getAnswer())) {
                    if (examSlip.getTopicDetail().getQuestions().getType().equals("Listening")) {
                        numberListenCorrect++;
                    }
                    if (examSlip.getTopicDetail().getQuestions().getType().equals("Reading")) {
                        numberReadCorrect++;
                    }
                }
            }
        }
        int sumListen = sumQuesByType("Listening", lstExamSlips);
        int sumRead = sumQuesByType("Reading", lstExamSlips);
        float listenPoint = 25 / sumListen * numberListenCorrect;
        float readPoint = 25 / sumRead * numberReadCorrect;
        Result result = new Result();
        result.setListenPoint(listenPoint);
        result.setReadPoint(readPoint);
        return result;
    }

    public int sumQuesByType(String type, List<ExamSlip> examSlip) {
        int count = 0;
        for (ExamSlip examSlip2 : examSlip) {
            if (examSlip2.getTopicDetail().getQuestions().getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    @GetMapping("/examDetail/{id}")
    public String examDetail(
            @PathVariable("id") Long id, HttpServletRequest request,
            Principal principal, Model model) {

        Result result = resultService.findResult(id);
        List<ExamSlip> lstExamSlips = examSlipService.findExamSlipByUser(result.getUserss().getId(),
                result.getExam().getIdTopic());
        List<ExamSlip> lstWrite = new ArrayList<ExamSlip>();
        for (ExamSlip iterable_element : lstExamSlips) {
            if (iterable_element.getTopicDetail().getQuestions().getType().equals("Writing")) {
                lstWrite.add(iterable_element);
            }
        }
        model.addAttribute("lstWrite", lstWrite);
        return "Admin/examDetail";
    }

}
