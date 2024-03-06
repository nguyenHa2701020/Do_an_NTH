package com.doan.elearning.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.ClassDto;
import com.doan.elearning.dto.LevelDto;
import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Level;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.LevelService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EclassController {
    private final ClassService cs;
    private final LevelService ls;
    private final UserService us;

    @RequestMapping("/eclass")
    public String lst(Model model) {
        model.addAttribute("title", "Manage Class");
        List<Eclass> ec = cs.findAll();
        List<Level> lv = ls.findAll();

        List<Users> lstAll = us.findALl();
        List<Users> lstLecture = listLec(lstAll);
        model.addAttribute("lstLecture", lstLecture);

        model.addAttribute("eclass", ec);

        model.addAttribute("level", lv);
        model.addAttribute("size", ec.size());
        model.addAttribute("classDto", new ClassDto());

        return "Admin/eclass";

    }

    public List<Users> listLec(List<Users> lst) {
        List<Users> lu = new ArrayList<Users>();
        for (Users users : lst) {
            List<Role> roles = users.getRoles();
            if (roles.size() == 1) {
                if (roles.get(0).getId() == 3) {
                    lu.add(users);
                }

            }

        }
        return lu;
    }

    @PostMapping("/save-eclass")
    public String addLevel(@ModelAttribute("classDto") ClassDto classDto,
            RedirectAttributes redirectAttributes) {
        try {
            cs.save(classDto);
            redirectAttributes.addFlashAttribute("success", "Add new level successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new level!");
        }
        return "redirect:/eclass";

    }

    @RequestMapping("/teachingclass")
    public String teachClass(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());

            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            
            List<Eclass> ec = cs.findByidGV(usk.getId());
            model.addAttribute("eclass", ec);
            model.addAttribute("currentPages", "teachingclass");


        }
        return "Lecturers/teachingclass";
    }

    @RequestMapping("/learningclass")
    public String learningclass(Model model, Principal principal, Authentication authentication) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());

            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            
            Eclass ec = cs.findByLgid(usk.getIdClass());
            model.addAttribute("eclass", ec);
            model.addAttribute("currentPages", "learningclass");

        }
        return "Lecturers/teachingclass";
    }
}
