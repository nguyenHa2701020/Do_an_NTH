package com.doan.elearning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.ClassDto;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Result;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.ResultService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/result")
public class ResultApi {
    @Autowired
    private ResultService resultService;

    @GetMapping("/{id}")
    public ResponseEntity<Void> getAll(HttpServletResponse httpServletResponse, @PathVariable Long id) {
        List<Result> lstResults = resultService.findResultByExam(id);
        try {
            resultService.exportToExcel(lstResults, httpServletResponse);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return ResponseEntity.ok().build();
    }

    // @PostMapping("/save")
    // public ResponseEntity<Eclass> saveClass(@ModelAttribute ClassDto classDto) {
    // Eclass eclass = classService.save(classDto);
    // return ResponseEntity.ok().build();
    // }
}
