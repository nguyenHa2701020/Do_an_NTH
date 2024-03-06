package com.doan.elearning.service;

import java.util.List;
import java.util.Optional;

import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.entity.Level;

public interface LevelService {
    Level save(LevelDto leveldto);

 List<Level> findAll();
 void delete(Long id);
  Optional<Level> findById(Long id);
}
