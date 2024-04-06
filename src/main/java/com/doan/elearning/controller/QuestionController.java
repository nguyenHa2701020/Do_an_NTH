package com.doan.elearning.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.doan.elearning.dto.QuestionDto;

import com.doan.elearning.entity.Level;
import com.doan.elearning.entity.Questions;

import com.doan.elearning.service.LevelService;
import com.doan.elearning.service.QuestionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final LevelService lv;

    @RequestMapping("/questions")
    public String lst(Model model) {
        model.addAttribute("title", "Manage questions");
        List<Questions> questions = questionService.findAll();
        List<Level> level = lv.findAll();
        model.addAttribute("level", level);
        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());
        model.addAttribute("questionDto", new QuestionDto());
        return "Admin/questions";

    }

    @PostMapping("/save-questions")
    public String addQuestion(@ModelAttribute("questionDto") QuestionDto questionDto,
                              RedirectAttributes redirectAttributes) {
        try {

            questionService.save(questionDto);
            redirectAttributes.addFlashAttribute("success", "Add new question successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new question!");
        }
        return "redirect:/questions";

    }

    @PostMapping("/uploadex")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idLevel") Long idLV) {
        try {
            // Đọc file Excel
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            // Chọn sheet trong file Excel
            Sheet sheet = workbook.getSheetAt(0);

            Optional<Level> level = lv.findById(idLV);
            Level level2 = new Level();
            // Lặp qua từng hàng trong sheet
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Đọc giá trị từ các ô trong hàng
                Cell type = row.getCell(0);
                Cell ques = row.getCell(1);
                Cell op1 = row.getCell(2);
                Cell op2 = row.getCell(3);
                Cell op3 = row.getCell(4);
                Cell op4 = row.getCell(5);
                Cell ans = row.getCell(6);

                if (level.isPresent()) {
                    level2 = level.get();
                }

                QuestionDto quaDto = new QuestionDto();
                quaDto.setType(type.getStringCellValue());
                quaDto.setQuestion(ques.getStringCellValue());
                quaDto.setOption1(op1.getStringCellValue());
                quaDto.setOption2(op2.getStringCellValue());
                quaDto.setOption3(op3.getStringCellValue());
                quaDto.setOption4(op4.getStringCellValue());
                quaDto.setAnswer(ans.getStringCellValue());
                quaDto.setLevel(level2);
                questionService.save(quaDto);


            }

            // Đóng workbook
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Chuyển hướng trang sau khi upload thành công
        return "redirect:/questions";
    }

    @RequestMapping(value = "/delete-questions", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            questionService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of level, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/questions";
    }
}
