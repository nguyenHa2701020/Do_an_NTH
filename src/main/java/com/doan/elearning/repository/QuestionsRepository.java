package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Questions;

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    @Query("select p from Questions p")
    List<Questions> findAll();

    @Query("select p from Questions p where p.type=?1  order by rand() limit ?2")
    List<Questions> randomQuestion(String type, int number);

    @Query("select o from Questions o where o.id = ?1")
    Questions findByLgid(Long id);
}