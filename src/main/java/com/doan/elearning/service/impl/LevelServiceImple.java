package com.doan.elearning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.LevelDto;

import com.doan.elearning.entity.Level;
import com.doan.elearning.repository.LevelRepository;
import com.doan.elearning.service.LevelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LevelServiceImple implements LevelService {
    private final LevelRepository levelRepository;

    @Override
    public List<Level> findAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level save(LevelDto leveldto) {
        Level level = new Level();
        level.setName(leveldto.getName());
        level.setCourse(leveldto.getCourse());
        return levelRepository.save(level);
    }

    @Override
    public void delete(Long id) {
        Optional<Level> level = levelRepository.findById(id);

        if (level.isPresent()) {
            Level level2 = level.get();
            levelRepository.delete(level2);
        }

    }

    @Override
    public Optional<Level> findById(Long id) {
        return levelRepository.findById(id);
    }

    @Override
    public Level update(LevelDto levelDto) {

        Level levelUpdate = levelRepository.getReferenceById(levelDto.getId());

        levelUpdate.setId(levelUpdate.getId());
        levelUpdate.setName(levelDto.getName());
        levelUpdate.setCourse(levelDto.getCourse());
        return levelRepository.save(levelUpdate);

    }

    @Override
    public List<Level> findLevel(String keyword) {
        return levelRepository.findByName(keyword);
    }

}
