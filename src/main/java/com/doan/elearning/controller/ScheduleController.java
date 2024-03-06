package com.doan.elearning.controller;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
    public String lsss(Model model, Principal principal) {

        if (principal != null) {

            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());

            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

            Eclass cl = cs.findByLgid(usk.getIdClass());
            List<Schedule> ls = lv.findByLgid(cl.getId());

            int size = ls.size() - 1;
            Date st = ls.get(0).getDatelearn();
            Date en = ls.get(size).getDatelearn();

            LocalDate nw = LocalDate.now();
            LocalDate stt = st.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate enn = en.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            model.addAttribute("currentDate", nw);

            if (nw.isAfter(stt) && nw.isBefore(enn)) {
                // Ngày xác định nằm trong khoảng thời gian từ A đến B
                // Thêm các ngày trong tuần vào danh sách (list)
                List<LocalDate> weekDays = new ArrayList<>();
                LocalDate startOfWeek = nw.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endOfWeek = nw.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

                /// neww
                List<Map<String, String>> dataList = new ArrayList<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                /// -----

                while (!startOfWeek.isAfter(endOfWeek)) {
                    Map<String, String> data1 = new HashMap<>();
                    // Lấy thứ từ LocalDate
                    String dayOfWeek = startOfWeek.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
                    // Chuyển đổi LocalDate thành chuỗi theo định dạng
                    String formattedDate = startOfWeek.format(formatter);
                    data1.put(dayOfWeek, formattedDate);
                    dataList.add(data1);

                    weekDays.add(startOfWeek);
                    startOfWeek = startOfWeek.plusDays(1);
                }

                List<LocalDate> kho = new ArrayList<LocalDate>();
                for (Schedule iterable_element : ls) {
                    kho.add(iterable_element.getDatelearn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }

                List<LocalDate> ll = new ArrayList<LocalDate>();
                List<LocalDate> newList = new ArrayList<LocalDate>();

                for (LocalDate iterable_element : weekDays) {
                    for (LocalDate localDate : kho) {
                        if (iterable_element.equals(localDate)) {
                            ll.add(iterable_element);

                        }

                    }

                }

                for (int i = 0; i < weekDays.size(); i++) {
                    if (ll.contains(weekDays.get(i))) {
                        newList.add(weekDays.get(i));
                    } else {
                        newList.add(null);
                    }
                }

                List<Schedule> sc= new ArrayList<>();
                for (int i = 0; i < weekDays.size(); i++) {
                    boolean check=false;
                    for (Schedule iterable_element : ls) {
                        if(iterable_element.getDatelearn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(weekDays.get(i))){
                            sc.add(iterable_element);
                            check=true;
                            break;
                        }
                    }
                    if(check==false){
                        sc.add(null);
                    }
                }

                model.addAttribute("schedule7", dataList);

                model.addAttribute("schedule", ls);

                model.addAttribute("valueschedule", sc);

                model.addAttribute("currentPages", "schedule");

                return "schedulestudent";
            }


        }
        return "Student/schedulestudent";
    }

    @PostMapping("/schedulestudent")
    public String lsuss(@RequestParam("date") LocalDate date, Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("namelogin", principal.getName());
            Users usk = us.findByUsername(principal.getName());

            model.addAttribute("currentDate", date);

            List<Role> rl = usk.getRoles();
            if (rl.size() == 1) {
                model.addAttribute("rolelogin", rl.get(0).getName());
            }

            Eclass cl = cs.findByLgid(usk.getIdClass());
            List<Schedule> ls = lv.findByLgid(cl.getId());

            int size = ls.size() - 1;
            Date st = ls.get(0).getDatelearn();
            Date en = ls.get(size).getDatelearn();

            LocalDate nw = date;
            LocalDate stt = st.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate enn = en.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (nw.isAfter(stt) && nw.isBefore(enn)) {
                // Ngày xác định nằm trong khoảng thời gian từ A đến B
                // Thêm các ngày trong tuần vào danh sách (list)
                List<LocalDate> weekDays = new ArrayList<>();
                LocalDate startOfWeek = nw.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endOfWeek = nw.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

                 /// neww
                 List<Map<String, String>> dataList = new ArrayList<>();
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                 /// -----
 
                 while (!startOfWeek.isAfter(endOfWeek)) {
                     Map<String, String> data1 = new HashMap<>();
                     // Lấy thứ từ LocalDate
                     String dayOfWeek = startOfWeek.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
                     // Chuyển đổi LocalDate thành chuỗi theo định dạng
                     String formattedDate = startOfWeek.format(formatter);
                     data1.put(dayOfWeek, formattedDate);
                     dataList.add(data1);
 
                     weekDays.add(startOfWeek);
                     startOfWeek = startOfWeek.plusDays(1);
                 }

                List<LocalDate> kho = new ArrayList<LocalDate>();
                for (Schedule iterable_element : ls) {
                    kho.add(iterable_element.getDatelearn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                }

                List<LocalDate> ll = new ArrayList<LocalDate>();
               

                for (LocalDate iterable_element : weekDays) {
                    for (LocalDate localDate : kho) {
                        if (iterable_element.equals(localDate)) {
                            ll.add(iterable_element);

                        }

                    }

                }

                List<Schedule> sc= new ArrayList<>();
                for (int i = 0; i < weekDays.size(); i++) {
                    boolean check=false;
                    for (Schedule iterable_element : ls) {
                        if(iterable_element.getDatelearn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().equals(weekDays.get(i))){
                            sc.add(iterable_element);
                            check=true;
                            break;
                        }
                    }
                    if(check==false){
                        sc.add(null);
                    }
                }

                model.addAttribute("schedule7", dataList);

                model.addAttribute("schedule", ls);

                model.addAttribute("valueschedule", sc);
                return "Student/schedulestudent";
            }

        }
        return "Student/schedulestudent";
    }

    @PostMapping("/save-schedule")
    public String addLevel(@ModelAttribute("scheduleDto") ScheduleDto scheduleDto,
            RedirectAttributes redirectAttributes) {
        try {
            String number = scheduleDto.getNumberdate();
            // Chuyển đổi chuỗi thành danh sách các số nguyên
            List<Integer> days = Arrays.stream(number.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            // Chuyển đổi từ số nguyên sang đối tượng DayOfWeek
            List<DayOfWeek> dayOfWeeks = days.stream()
                    .map(DayOfWeek::of)
                    .collect(Collectors.toList());

            Eclass cc = cs.findByLgid(scheduleDto.getIdclass());
            scheduleDto.setEclass(cc);
            LocalDate curent = cc.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            LessonDto lsd= new LessonDto();
            Users userj= us.findByid(cc.getIdGV());
            lsd.setEclass(cc);
            lsd.setUserss(userj);
            int count = 0;
            while (count <= 20) {
                if (dayOfWeeks.contains(curent.getDayOfWeek())) {
                    scheduleDto.setDatelearn(Date.from(curent.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    // lv.save(scheduleDto);

                    String numberLesson= "Lesson "+(count+1);
                    lsd.setName(numberLesson);
                    lessonService.save(lsd);
                    lv.save(scheduleDto);

                    count++;
                }
                curent = curent.plusDays(1);
            }

            // lv.save(scheduleDto);
            redirectAttributes.addFlashAttribute("success", "Add new level successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to add new level!");
        }
        return "redirect:/schedule";

    }

}
