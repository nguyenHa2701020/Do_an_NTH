package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.entity.Level;
import com.doan.elearning.repository.LevelRepository;
import com.doan.elearning.service.LevelService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LevelServiceImple implements LevelService{
private final LevelRepository levelRepository;
    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level save(LevelDto leveldto) {
        Level level= new Level();
        level.setName(leveldto.getName());
        level.setCourse(leveldto.getCourse());
        return levelRepository.save(level);
    }
    
}
