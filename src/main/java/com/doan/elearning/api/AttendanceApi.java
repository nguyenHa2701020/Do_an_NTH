package com.doan.elearning.api;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.AttendanceResponse;
import com.doan.elearning.entity.Attendances;

import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Users;

import com.doan.elearning.service.AttendancesService;
import com.doan.elearning.service.ClassService;
import com.doan.elearning.service.LessonService;
import com.doan.elearning.service.UserService;

@RestController
@RequestMapping("/api/attendance/")
public class AttendanceApi {
    @Autowired
    private ClassService classService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;
    @Autowired
    private AttendancesService attendanceService;

    @RequestMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAll(@PathVariable Long id) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        AttendanceResponse attendanceResponse = new AttendanceResponse();
        // Eclass eclass= classService.findByLgid(id);
        List<Lesson> lstLesson = lessonService.findLessonByClass(id);
        List<Users> lstUser = userService.findByIdClass(id);
        List<String> title = new ArrayList<>();
        title.add("Ho va ten");

        for (Lesson lesson : lstLesson) {
            LocalDate stt = lesson.getSchedule().getDatelearn().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate();
            title.add(stt.format(formatter).toString());

        }
        attendanceResponse.setTitle(title);
        List<Map<String, List<Attendances>>> content = new ArrayList<>();

        for (Users user : lstUser) {
            List<Attendances> lstAttendances = new ArrayList<>();
            Map<String, List<Attendances>> mapAttendance = new HashMap<>();

            for (Lesson lesson : lstLesson) {

                Attendances attendances = attendanceService.findByUserAndLesson(user.getId(), lesson.getId());
                lstAttendances.add(attendances);

            }
            mapAttendance.put(user.getUsername(), lstAttendances);
            content.add(mapAttendance);
        }

        attendanceResponse.setContent(content);
        return ResponseEntity.ok(attendanceResponse);

    }
    @PatchMapping("/updateAtten/{id}")
    public ResponseEntity<Attendances> updateAttendance(@PathVariable Long id)
    {
        attendanceService.updateAtten(id);
return ResponseEntity.ok().build();
    }

}
