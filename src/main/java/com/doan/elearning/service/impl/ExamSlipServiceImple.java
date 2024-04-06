package com.doan.elearning.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.ExamSlipDto;

import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.ExamSlip;
import com.doan.elearning.entity.Result;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.entity.Users;
import com.doan.elearning.repository.ExamSlipRepository;
import com.doan.elearning.repository.ResultRepository;
import com.doan.elearning.repository.TopicDetailRepository;
import com.doan.elearning.repository.TopicRepository;
import com.doan.elearning.service.ExamSlipService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamSlipServiceImple implements ExamSlipService {
    private final ExamSlipRepository examSlipRepository;
    private final TopicRepository topicRepository;
    private final ResultRepository resultRepository;
    private final TopicDetailRepository topicDetailRepository;

    @Override
    public List<ExamSlip> findAll() {
        return examSlipRepository.findAll();
    }

    @Override
    public List<ExamSlip> findExamSlipByUser(Long idUser, Long idTopic) {
        return examSlipRepository.findExamSlipByUser(idUser, idTopic);
    }

    @Override
    public ExamSlip save(ExamSlipDto examSlipDto) {
        ExamSlip examSlip = new ExamSlip();
        examSlip.setAnswer(examSlipDto.getAnswer());
        examSlip.setTopicDetail(examSlipDto.getTopicDetail());
        examSlip.setUsers(examSlipDto.getUsers());
        return examSlipRepository.save(examSlip);
    }

    @Override
    public ExamSlip update(String answer, Long id) {
        ExamSlip examSlipUpdate = examSlipRepository.getReferenceById(id);

        examSlipUpdate.setAnswer(answer);
        return examSlipRepository.save(examSlipUpdate);
    }

    @Override
    public ExamSlip finfExamSlip(Long id) {
        return examSlipRepository.finfExamSlip(id);
    }

    @Override
    public void createExamSlip(Users user, Exam exam) {
        //    Optional<Topic> topic = topicRepository.findById();
        List<TopicDetail> topicDetail = topicDetailRepository.findTopicDetailByTopic(exam.getIdTopic());
        ExamSlip examSlip = new ExamSlip();
        examSlip.setUsers(user);
        for (TopicDetail topicDetail2 : topicDetail) {
            examSlip.setTopicDetail(topicDetail2);
            examSlipRepository.save(examSlip);
        }

        Result result = new Result();
        result.setExam(exam);
        result.setUserss(user);
        result.setListenPoint(0f);
        result.setReadPoint(0f);
        result.setSpeakPoint(0f);
        result.setWritePoint(0f);

        resultRepository.save(result);
    }

    @Override
    public boolean checkTime(String dateExam, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();

        Date endTimee = new Date();
        try {

            endTimee = dateFormat.parse(dateExam + " " + endTime + ":00");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar current = Calendar.getInstance();
        current.setTime(currentTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTimee);
        boolean check = (current.before(end) || current.equals(end));
        return check;
    }


}

