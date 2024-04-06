package com.doan.elearning.service;

import java.util.List;
import java.util.Optional;

import com.doan.elearning.dto.LevelDto;

import com.doan.elearning.entity.Level;

public interface LevelService {
    Level save(LevelDto leveldto);

    Level update(LevelDto levelDto);

    List<Level> findAll();

    void delete(Long id);

    Optional<Level> findById(Long id);

    List<Level> findLevel(String keyword);
}
