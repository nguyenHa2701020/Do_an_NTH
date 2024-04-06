package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("select o from Exam o where o.eclass.id = ?1")
    List<Exam> findExamByClass(Long id);

    @Query("select o from Exam o ")
    List<Exam> findAll();

    @Query("select o from Exam o where o.id = ?1")
    Exam findExam(Long id);

    @Query(value = "update Exam set status = ?1 where id=?2")
    Exam update(boolean status, Long id);

}
