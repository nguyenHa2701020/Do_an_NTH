package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Exam;
import com.doan.elearning.entity.ExamSlip;

@Repository
public interface ExamSlipRepository extends JpaRepository<ExamSlip, Long> {
    @Query("select o from ExamSlip o where o.users.id = ?1 and o.topicDetail.topic.id= ?2 ")
    List<ExamSlip> findExamSlipByUser(Long idUser, Long idTopic);

    @Query("select o from ExamSlip o ")
    List<ExamSlip> findAll();

    @Query(value = "update ExamSlip set answer = ?1 where id=?2")
    ExamSlip update(String answer, Long id);

}
