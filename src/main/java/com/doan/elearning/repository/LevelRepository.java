package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Level;
@Repository
public interface LevelRepository extends JpaRepository<Level, Long>{
 @Query("select p from Level p")
    List<Level> findAll();
}
