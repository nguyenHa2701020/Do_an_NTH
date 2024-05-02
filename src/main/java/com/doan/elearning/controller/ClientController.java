package com.doan.elearning.controller;

import java.security.Principal;

import java.util.List;

import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.CourseService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final UserService us;
    private final CourseService usk;

    @GetMapping("/")
    public String home(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

        }
        //else{
        //   return "redirect:/login";
        // }
        List<Course> courses = usk.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("currentPages", "home");
        return "Client/home";
    }
    @GetMapping("/forum")
    public String forum() {
        return "Client/forum";
    }
    @GetMapping("/admin")
    public String ad() {
        return "Admin/indexAd";
    }

    @GetMapping("/error403")
    public String error() {
        return "Client/error403";
    }

    @GetMapping("/about")
    public String ab(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

        }
        model.addAttribute("currentPages", "about");
        return "Client/about";
    }


}
