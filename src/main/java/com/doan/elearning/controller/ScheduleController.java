package com.doan.elearning.controller;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Arrays;
import java.util.Date;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doan.elearning.dto.LessonDto;

import com.doan.elearning.dto.ScheduleDto;

import com.doan.elearning.entity.Eclass;

import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.entity.Users;

import com.doan.elearning.service.ClassService;

import com.doan.elearning.service.LessonService;

import com.doan.elearning.service.ScheduleService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService lv;
    private final ClassService cs;

    private final UserService us;
    private final LessonService lessonService;

    @RequestMapping("/schedule")
    public String lst(Model model) {

        model.addAttribute("title", "Manage Level");
        List<Schedule> schedule = lv.findAll();
        List<Eclass> classs = cs.findAll();
        model.addAttribute("schedule", schedule);
        model.addAttribute("class", classs);
        model.addAttribute("size", schedule.size());
        model.addAttribute("scheduleDto", new ScheduleDto());
        return "Admin/schedule";

    }

    @RequestMapping("/schedulestudent")
    public String lsss(Model model, Principal principal, @RequestParam("role") String role,
                       @RequestParam(value = "idClass", required = false) Long idClass) {
        Long id = 0L;
        if (principal != null) {

            model.addAttribute("namelogin", principal.getName());

            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            if (role.equals("std")) {
                id = usk.getIdClass();
            }

            if (role.equals("lct")) {
                id = idClass;

            }
            model.addAttribute("idClass", id);
            model.addAttribute("currentDate", LocalDate.now());
            model.addAttribute("schedule7", lv.dayofWeekGV(LocalDate.now(), id));

            model.addAttribute("valueschedule", lv.sheduleOfWeekGV(LocalDate.now(), id));

            model.addAttribute("currentPages", "schedule");

            return "Student/schedulestudent";
        }

        return "Student/schedulestudent";
    }

    @PostMapping("/schedulestudent")
    public String lsuss(@RequestParam("date") LocalDate date, Model model, Principal principal,
                        @RequestParam("role") String role, @RequestParam(value = "idClass", required = false) Long idClass) {
        Long id = 0L;
        if (principal != null) {

            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());

            model.addAttribute("currentDate", date);

            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }
            if (role.equals("std")) {
                id = usk.getIdClass();
            }
            if (role.equals("lct")) {
                id = idClass;

            }
            model.addAttribute("idClass", id);
            model.addAttribute("currentDate", date);
            model.addAttribute("schedule7", lv.dayofWeekGV(date, id));

            // model.addAttribute("schedule", ls);

            model.addAttribute("valueschedule", lv.sheduleOfWeekGV(date, id));
            return "Student/schedulestudent";
        }
        return "Student/schedulestudent";
    }

    // @PostMapping("/save-schedule")
    // public String addLevel(@ModelAttribute("scheduleDto") ScheduleDto scheduleDto,
    //                        RedirectAttributes redirectAttributes) {
    //     try {
    //         String number = scheduleDto.getNumberdate();
    //         // Chuyển đổi chuỗi thành danh sách các số nguyên
    //         List<Integer> days = Arrays.stream(number.split(","))
    //                 .map(Integer::parseInt)
    //                 .collect(Collectors.toList());

    //         // Chuyển đổi từ số nguyên sang đối tượng DayOfWeek
    //         List<DayOfWeek> dayOfWeeks = days.stream()
    //                 .map(DayOfWeek::of)
    //                 .collect(Collectors.toList());

    //         Eclass cc = cs.findByLgid(scheduleDto.getIdclass());
    //         scheduleDto.setEclass(cc);
    //         LocalDate curent = cc.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    //         LessonDto lsd = new LessonDto();
    //         Users userj = us.findByid(cc.getIdGV());
    //         lsd.setEclass(cc);
    //         lsd.setUserss(userj);
    //         int count = 0;
    //         while (count <= 20) {
    //             if (dayOfWeeks.contains(curent.getDayOfWeek())) {
    //                 scheduleDto.setDatelearn(Date.from(curent.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    //                 // lv.save(scheduleDto);

    //                 String numberLesson = "Lesson " + (count + 1);
    //                 lsd.setName(numberLesson);
    //                 lessonService.save(lsd);
    //                 lv.save(scheduleDto);

    //                 count++;
    //             }
    //             curent = curent.plusDays(1);
    //         }

    //         // lv.save(scheduleDto);
    //         redirectAttributes.addFlashAttribute("success", "Add new schedule successfully!");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         redirectAttributes.addFlashAttribute("error", "Failed to add new schedule!");
    //     }
    //     return "redirect:/schedule";

    // }
}
