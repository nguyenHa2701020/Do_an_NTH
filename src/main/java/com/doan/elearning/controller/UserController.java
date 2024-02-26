package com.doan.elearning.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @RequestMapping(value = "/delete-users", method = {RequestMethod.GET, RequestMethod.PUT})
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
        return "redirect:/user";
    }


}
