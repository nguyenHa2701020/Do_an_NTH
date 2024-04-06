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

import com.doan.elearning.dto.ClassDto;

import com.doan.elearning.entity.Eclass;

import com.doan.elearning.service.ClassService;

@RestController
@RequestMapping("api/eclass")
public class EclassApi {
    @Autowired
    private ClassService classService;

    @GetMapping("/")
    public ResponseEntity<List<Eclass>> getAll() {
        List<Eclass> lstClass = classService.findAll();
        return ResponseEntity.ok(lstClass);
    }

       @PostMapping("/save")
    public ResponseEntity<Eclass> saveClass(@ModelAttribute ClassDto classDto) {
        Eclass eclass = classService.save(classDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Eclass> getById(@PathVariable Long id)
    {
        Eclass eclass= classService.findByLgid(id);
        return ResponseEntity.ok(eclass);
    }
    @PutMapping("/update")
    public ResponseEntity<Void> updateClass(@ModelAttribute ClassDto classDto)
    {
        classService.update(classDto);
        return ResponseEntity.ok().build();
    }
@DeleteMapping("/delete/{id}")
public ResponseEntity<Void> deleteClass(@PathVariable Long id)
{
    classService.deleteClass(id);
    return ResponseEntity.ok().build();
}
}
