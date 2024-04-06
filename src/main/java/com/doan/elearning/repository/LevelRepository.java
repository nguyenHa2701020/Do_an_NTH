package com.doan.elearning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.doan.elearning.entity.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    @Query("select p from Level p")
    List<Level> findAll();

    Optional<Level> findById(Long id);

    @Query(value = "update Level set name = ?1 where id=?2")
    Level update(String name, Long id);

    @Query("select p from Level p where p.name like %?1%")
    List<Level> findByName(String name);
}
