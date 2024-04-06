package com.doan.elearning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.entity.Level;
import com.doan.elearning.service.LevelService;

@RestController
@RequestMapping("/api/level")
public class LevelApi {
    @Autowired
    private LevelService levelService;

    @GetMapping("/")
    public ResponseEntity<List<Level>> getAll() {
        List<Level> lstLevel = levelService.findAll();
        return ResponseEntity.ok(lstLevel);
    }

    @PostMapping("/save")
    public ResponseEntity<Level> saveLevel(@ModelAttribute LevelDto levelDto) {
        Level level = levelService.save(levelDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> getById(@PathVariable Long id) {
        Level level = levelService.findById(id).get();
        return ResponseEntity.ok(level);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateLevel(@ModelAttribute LevelDto levelDto) {
        levelService.update(levelDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        levelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
