package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("select o from Result o where o.userss.id = ?1 and o.exam.id= ?2 ")
    Result findResultByUser(Long idUser, Long idExam);

    @Query("select o from Result o ")
    List<Result> findAll();

    @Query("select o from Result o where o.exam.id= ?1 ")
    List<Result> findResultByExam(Long idExam);

    @Query("select o from Result o where o.id = ?1 ")
    Result findResult(Long idResult);
}
