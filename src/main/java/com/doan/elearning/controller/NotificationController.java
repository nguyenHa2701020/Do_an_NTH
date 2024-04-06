package com.doan.elearning.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doan.elearning.entity.Course;
import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Notification;
import com.doan.elearning.entity.Role;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.NotificationService;
import com.doan.elearning.service.ScheduleService;
import com.doan.elearning.service.UserService;
import com.doan.elearning.utils.EmailUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    private UserService us;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping("/notification")
    public String test(Model model, Principal principal, @RequestParam("id") Long id) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());

            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());

            }

            Schedule schedule = scheduleService.findById(id);
            Notification notification = new Notification();
            notification.setSchedule(schedule);
            model.addAttribute("notifications", notification);
            return "Lecturers/notification";

        }
        return "Client/login";
    }

    @PostMapping("/create-notification")
    public String postMethodName(@ModelAttribute("notifications") Notification notification, Principal principal,
                                 Model model, HttpServletRequest request) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());

            Users usk = us.findByUsername(principal.getName());
            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());

            }
        }
        notification.setDayContact(new Date());
        notification.setSender(principal.getName());
        notificationService.save(notification);
        scheduleService.updateNotification(notification.getSchedule().getId(), true);

        Schedule schedule = scheduleService.findById(notification.getSchedule().getId());
        List<Users> users = us.findByIdClass(schedule.getEclass().getId());
        List<String> email = users.stream().map(Users::getEmail).collect(Collectors.toList());
        emailUtil.sendEmail(email, notification.getTitle(), notification.getContent());

        return "redirect:" + request.getHeader("Referer");

    }

    @RequestMapping(value = "/findByNotification", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public List<Notification> findByNotification(Long id) {
        return notificationService.findByIdSchedule(id);
    }

    @RequestMapping(value = "/findByNotificationId", method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Optional<Notification> findById(Long id) {
        return notificationService.findById(id);
    }
}
