package com.doan.elearning.controller;

import java.nio.file.Files;
import java.security.Principal;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.context.annotation.Conditional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.CourseDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CourseController {
    private final CourseService us;

    @RequestMapping("/course")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Course");
        List<Course> courses = us.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("size", courses.size());
        model.addAttribute("usernew", new Users());
        model.addAttribute("courseDto", new CourseDto());
        return "course";

    }

    @PostMapping("/save-course")
    public String addCourse(@ModelAttribute("courseDto") CourseDto course,
            @RequestParam("imageProduct") MultipartFile imageProduct,
            RedirectAttributes redirectAttributes) {
        try {

            us.save(imageProduct, course);
            redirectAttributes.addFlashAttribute("success", "Add new course successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new course!");
        }
        return "redirect:/course";

    }

    @RequestMapping(value = "/findByCourseId", method = { RequestMethod.PUT, RequestMethod.GET })
    @ResponseBody
    public Optional<Course> findCourseId(Long id) {
        return us.findById(id);
    }

    // @GetMapping("/update-course")
    // public String update(Course course, RedirectAttributes redirectAttributes) {
    //     try {
    //         us.update(course);
    //         redirectAttributes.addFlashAttribute("success", "Update successfully!");
    //     } catch (DataIntegrityViolationException e1) {
    //         e1.printStackTrace();
    //         redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
    //     } catch (Exception e2) {
    //         e2.printStackTrace();
    //         redirectAttributes.addFlashAttribute("error",
    //                 "Error from server or duplicate name of category, please check again!");
    //     }
    //     return "redirect:/course";
    // }
    @RequestMapping(value = "/delete-course", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            us.delete(id);
            redirectAttributes.addFlashAttribute("success", "Deleted successfully!");
        } catch (DataIntegrityViolationException e1) {
            e1.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
        } catch (Exception e2) {
            e2.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server");
        }
        return "redirect:/course";
    }

    @GetMapping("/search-course/{pageNo}")
    public String searchProduct(
                                @RequestParam(value = "keyword") String keyword,
                                Model model, Principal principal
    ) {
        if (principal == null) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Course");
        List<Course> courses = us.findCourses(keyword);
        model.addAttribute("courses", courses);
        model.addAttribute("size", courses.size());
        model.addAttribute("usernew", new Users());
        model.addAttribute("courseDto", new CourseDto());
        return "course";

    }

    @GetMapping("/update-course/{id}")
    public String updateCourse(@PathVariable("id") Long id, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
       
        Optional<Course> vv=us.findById(id);

        CourseDto courseDto = new CourseDto();
        courseDto.setId(vv.get().getId());
        courseDto.setName(vv.get().getName());
        courseDto.setImage(vv.get().getImage());

        model.addAttribute("title", "Add Product");
        model.addAttribute("courseDto", courseDto);
        return "update-course";
    }

    @PostMapping("/update-course/{id}")
    public String updateProduct(@ModelAttribute("courseDto") CourseDto courseDto,
                                @RequestParam("imageProduct") MultipartFile imageProduct,
                                RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            us.update(imageProduct, courseDto);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/course";
    }
}
