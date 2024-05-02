package com.doan.elearning.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.Principal;
import java.util.List;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.LessonService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final UserService us;

    @GetMapping("/lesson")
    public String lesson(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

        }
        List<Lesson> ls = lessonService.findLessonByClass(id);
        model.addAttribute("lessons", ls);
        model.addAttribute("size", ls.size());
        model.addAttribute("idClass", id);
        return "Admin/lesson";

    }

    @GetMapping("/addlesson")
    public String addlesson(Long id, Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            Lesson lesson = lessonService.findLessons(id);
            model.addAttribute("lesson", lesson);

        }

        return "Lecturers/addlesson";
    }

    @PostMapping("/update-lesson/{id}")
    public String updateLesson(@ModelAttribute("lesson") Lesson lesson,
                               @RequestParam("file") MultipartFile document,
                               RedirectAttributes redirectAttributes, Principal principal) {
        try {

            String fileName = document.getOriginalFilename();
            File file = new File("D:\\springboot\\elearning\\src\\main\\resources\\static\\Upload", fileName);
            document.transferTo(file);

            lesson.setDocument(fileName);
            lessonService.updateLessons(lesson);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/lesson?id=" + lesson.getEclassId();
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {

        // Tạo một đối tượng File từ tên tập tin được yêu cầu.
        File file = new File("D:\\springboot\\elearning\\src\\main\\resources\\static\\Upload\\" + fileName);

        // Tạo một InputStream từ đối tượng File.
        InputStream inputStream = new FileInputStream(file);

        // Tạo một đối tượng InputStreamResource từ InputStream.
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        // Thiết lập các header để trình duyệt có thể tải xuống file.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        // Trả về đối tượng ResponseEntity bao lấy InputStreamResource và các header
        // tương ứng.
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }

}
