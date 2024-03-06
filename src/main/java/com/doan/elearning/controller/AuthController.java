package com.doan.elearning.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doan.elearning.dto.RoleDto;
import com.doan.elearning.dto.UserDto;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.RoleService;
import com.doan.elearning.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService adminService;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ClassService us;
    private final RoleService rs;

    @RequestMapping("/login")
    public String login(Model model) {

        List<Role> ls = rs.findALl();
        if (ls.isEmpty()) {
            RoleDto ad = new RoleDto();
            ad.setName("ADMIN");
            rs.save(ad);
            adminService.saveAdmin();

            RoleDto cs = new RoleDto();
            cs.setName("STUDENT");
            rs.save(cs);

            RoleDto lec = new RoleDto();
            lec.setName("LECTURER");
            rs.save(lec);
        }

        model.addAttribute("title", "Login Page");
        return "Client/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<Eclass> cc = us.findAll();
        model.addAttribute("class", cc);
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new UserDto());
        return "Admin/register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto") UserDto adminDto,
            BindingResult result,
            Model model) {

        try {

            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                return "Admin/register";
            }
            String username = adminDto.getId();
            Users admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDto", adminDto);
                System.out.println("admin not null");
                model.addAttribute("emailError", "Your email has been registered!");
                return "Admin/register";
            }
           
                adminDto.setPassword(passwordEncoder.encode("123456"));
                adminService.save(adminDto);
                System.out.println("success");
                model.addAttribute("success", "Register successfully!");
                model.addAttribute("adminDto", adminDto);
           
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "Admin/register";

    }
}
