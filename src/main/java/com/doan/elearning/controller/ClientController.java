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

  @GetMapping("/home")
  public String home(Model model, Principal principal, Authentication authentication) {
    if (principal != null) {
      model.addAttribute("namelogin", principal.getName());
      Users usk = us.findByLgid(principal.getName());
      List<Role> rl = usk.getRoles();
      if (rl.size() == 1) {
        model.addAttribute("rolelogin", rl.get(0).getName());
      }

    }
    List<Course> courses = usk.findAll();
    model.addAttribute("courses", courses);
    return "home";
  }

  @GetMapping("/admin")
  public String ad() {
    return "indexAd";
  }


  
}
