package com.doan.elearning.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import com.doan.elearning.entity.Users;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor


public class UserController {
    private final UserService us;
    @RequestMapping("/user")
    public String lst(Model model){
         model.addAttribute("title", "Manage User");
        List<Users> userss = us.findALl();
        model.addAttribute("usersss", userss);
        model.addAttribute("size", userss.size());
        model.addAttribute("usernew", new Users());
        return "users";
       
    }
    


}
