package com.doan.elearning.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.UserDto;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class UserController {
    private final UserService us;
    private final ClassService classService;
    @RequestMapping("/user")
    public String lst(Model model) {
        model.addAttribute("title", "Manage User");
        List<Users> userss = us.findALl();
        model.addAttribute("usersss", userss);
        List<Eclass> cc = classService.findAll();
        model.addAttribute("class", cc);
        model.addAttribute("size", userss.size());
        model.addAttribute("usernew", new Users());
        return "Admin/users";

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

    @GetMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        Users user = us.findByid(id);

        UserDto userDto = new UserDto();
        userDto.setIdPK(user.getId());

        userDto.setAddress(user.getAddress());

        userDto.setUsername(user.getUsername());

        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        model.addAttribute("title", "Update User");
        model.addAttribute("userDto", userDto);
        return "Admin/update-user";
    }

    @PostMapping("/update-user")
    public String updateProduct(@ModelAttribute("userDto") UserDto userDto,
                                RedirectAttributes redirectAttributes, Principal principal) {
        try {
            if (principal == null) {
                return "redirect:/login";
            }
            us.updatUsers(userDto);
            redirectAttributes.addFlashAttribute("success", "Update successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error server, please try again!");
        }
        return "redirect:/user";
    }

}
