package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Level;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.service.LevelService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LevelController {
    private final LevelService lv;
    private final CourseService cs;

    @RequestMapping("/level")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Level");
        List<Level> level = lv.findAll();
        List<Course> course = cs.findAll();
        model.addAttribute("level", level);
        model.addAttribute("course", course);
        model.addAttribute("size", level.size());
        model.addAttribute("levelDto", new LevelDto());
        return "Admin/level";

    }

    @PostMapping("/save-level")
    public String addLevel(@ModelAttribute("levelDto") LevelDto levelDto,
                           RedirectAttributes redirectAttributes) {
        try {

            lv.save(levelDto);
            redirectAttributes.addFlashAttribute("success", "Add new level successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new level!");
        }
        return "redirect:/level";

    }

    @RequestMapping(value = "/delete-level", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            lv.delete(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of level, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/level";
    }

    @GetMapping("/update-level/{id}")
    public String updateLevel(@PathVariable("id") Long id, Model model) {


        Optional<Level> level = lv.findById(id);

        LevelDto levelDto = new LevelDto();
        levelDto.setId(level.get().getId());
        levelDto.setName(level.get().getName());
        List<Course> course = cs.findAll();
        model.addAttribute("course", course);

        model.addAttribute("title", "Add Level");
        model.addAttribute("levelDto", levelDto);
        return "Admin/update-level";
    }

    @PostMapping("/update-level/{id}")
    public String updateProduct(@ModelAttribute("levelDto") LevelDto levelDto,
                                RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            lv.update(levelDto);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/level";
    }


    @GetMapping("/search-level/{pageNo}")
    public String searchProduct(
            @RequestParam(value = "keyword") String keyword,
            Model model, Principal principal
    ) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Level");
        List<Level> level = lv.findLevel(keyword);
        model.addAttribute("level", level);
        model.addAttribute("size", level.size());
        model.addAttribute("usernew", new Users());
        model.addAttribute("levelDto", new LevelDto());
        return "Admin/level";

    }

}
