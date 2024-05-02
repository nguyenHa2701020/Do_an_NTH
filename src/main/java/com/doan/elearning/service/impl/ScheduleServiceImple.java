package com.doan.elearning.service.impl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.elearning.dto.AttendancesDto;
import com.doan.elearning.dto.LessonDto;
import com.doan.elearning.dto.ScheduleDto;
import com.doan.elearning.entity.Attendances;
import com.doan.elearning.entity.Eclass;
import com.doan.elearning.entity.Lesson;
import com.doan.elearning.entity.Result;
import com.doan.elearning.entity.Schedule;
import com.doan.elearning.entity.Users;
import com.doan.elearning.repository.AttendancesRepository;
import com.doan.elearning.repository.ClassRepository;
import com.doan.elearning.repository.LessonRepository;
import com.doan.elearning.repository.ShceduleRepository;
import com.doan.elearning.repository.UsersRepository;
import com.doan.elearning.service.LessonService;
import com.doan.elearning.service.ScheduleService;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImple implements ScheduleService {
    private final ShceduleRepository sr;
    private final ClassRepository cs;
    private final LessonRepository lessonRepository;
    private final UsersRepository usersRepository;

    private final AttendancesRepository attendanceRepository;

    @Override
    public List<Schedule> findAll() {
        return sr.findAll();
    }

    @Override
    public void save(ScheduleDto scheduleDto) {

        String number = scheduleDto.getNumberdate();
        // Chuyển đổi chuỗi thành danh sách các số nguyên
        List<Integer> days = Arrays.stream(number.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        // Chuyển đổi từ số nguyên sang đối tượng DayOfWeek
        List<DayOfWeek> dayOfWeeks = days.stream()
                .map(DayOfWeek::of)
                .collect(Collectors.toList());

        Eclass cc = cs.findByLgid(scheduleDto.getEclass().getId());
        scheduleDto.setEclass(cc);
        LocalDate curent = cc.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LessonDto lsd = new LessonDto();
        Users userj = usersRepository.findByid(cc.getIdGV());
        lsd.setEclass(cc);
        lsd.setUserss(userj);
        int count = 0;
        while (count < scheduleDto.getNumberLesson()) {
            if (dayOfWeeks.contains(curent.getDayOfWeek())) {
                scheduleDto.setDatelearn(Date.from(curent.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                // lv.save(scheduleDto);

                // String numberLesson = "Lesson " + (count + 1);
                // lsd.setName(numberLesson);
                // lsd.setSchedule(convertToSchedule(scheduleDto));
                // lessonRepository.save(lsd);
                saveSchedule(scheduleDto);
                //saveLesson(lsd);

                count++;
            }
            curent = curent.plusDays(1);
        }
        count=0;
        List<Schedule> lstSchedule= sr.findByIdClass(cc.getId());
        for (Schedule schedule : lstSchedule) {
              String numberLesson = "Lesson " + (count + 1);
                lsd.setName(numberLesson);
                lsd.setSchedule(schedule);
                saveLesson(lsd);
                count++;
        }

        List<Users> lstUsers = usersRepository.findByIdClass(cc.getId());
        List<Lesson> lstLesson = lessonRepository.findLessonByClass(cc.getId());

        AttendancesDto attendance = new AttendancesDto();
        for (Lesson lesson : lstLesson) {
            for (Users user : lstUsers) {

                attendance.setLesson(lesson);
                attendance.setUserss(user);
                saveAtendance(attendance);
            }

        }

        //
    }
private Schedule convertToSchedule(ScheduleDto scheduleDto)
{
    Schedule sche = new Schedule();
    sche.setStudytime(scheduleDto.getStudytime());
    sche.setDatelearn(scheduleDto.getDatelearn());
    sche.setEclass(scheduleDto.getEclass());
    return sche;
}
    private void saveAtendance(AttendancesDto attendancedto) {
        Attendances attendance = new Attendances();
        attendance.setLesson(attendancedto.getLesson());
        attendance.setUserss(attendancedto.getUserss());
        attendanceRepository.save(attendance);
    }

    private void saveLesson(LessonDto lsd) {
        Lesson lesson = new Lesson();
        lesson.setEclass(lsd.getEclass());
        lesson.setUserss(lsd.getUserss());
        lesson.setName(lsd.getName());
        lesson.setSchedule(lsd.getSchedule());
        lessonRepository.save(lesson);
    }

    private Schedule saveSchedule(ScheduleDto scheduleDto) {
        Schedule sche = new Schedule();
        sche.setStudytime(scheduleDto.getStudytime());
        sche.setDatelearn(scheduleDto.getDatelearn());
        sche.setEclass(scheduleDto.getEclass());
        return sr.save(sche);
    }

    @Override
    public List<Schedule> findByIdClass(Long idlg) {

        return sr.findByIdClass(idlg);
    }

    public List<LocalDate> weekend(LocalDate localDate, Users usk) {
        List<LocalDate> weekDays = new ArrayList<>();
        Eclass cl = cs.findByLgid(usk.getIdClass());
        List<Schedule> ls = sr.findByIdClass(cl.getId());

        int size = ls.size() - 1;
        Date st = ls.get(0).getDatelearn();
        Date en = ls.get(size).getDatelearn();

        LocalDate nw = LocalDate.now();
        LocalDate stt = st.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate enn = en.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (nw.isAfter(stt) && nw.isBefore(enn)) {

            LocalDate startOfWeek = nw.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = nw.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            while (!startOfWeek.isAfter(endOfWeek)) {

                weekDays.add(startOfWeek);
                startOfWeek = startOfWeek.plusDays(1);
            }
            return weekDays;
        }
        return weekDays;
    }

    @Override
    public List<Map<String, String>> dayofWeekGV(LocalDate localDate, Long id) {

        List<Schedule> ls = sr.findByIdClass(id);
        int size = ls.size() - 1;
        Date st = ls.get(0).getDatelearn();
        Date en = ls.get(size).getDatelearn();

        LocalDate nw = localDate;
        LocalDate stt = st.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate enn = en.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<Map<String, String>> dataList = new ArrayList<>();
        if (nw.isAfter(stt) && nw.isBefore(enn)) {

            LocalDate startOfWeek = nw.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = nw.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            while (!startOfWeek.isAfter(endOfWeek)) {
                Map<String, String> data1 = new HashMap<>();

                String dayOfWeek = startOfWeek.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

                String formattedDate = startOfWeek.format(formatter);
                data1.put(dayOfWeek, formattedDate);
                dataList.add(data1);

                startOfWeek = startOfWeek.plusDays(1);
            }

            List<LocalDate> kho = new ArrayList<LocalDate>();
            for (Schedule iterable_element : ls) {
                kho.add(iterable_element.getDatelearn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }

        }
        return dataList;
    }

    @Override
    public List<Schedule> sheduleOfWeekGV(LocalDate localDate, Long id) {
        List<LocalDate> weekDays = weekendGV(localDate, id);
        List<Schedule> sc = new ArrayList<>();
        Eclass cl = cs.findByLgid(id);
        List<Schedule> ls = sr.findByIdClass(cl.getId());
        for (int i = 0; i < weekDays.size(); i++) {
            boolean check = false;
            for (Schedule iterable_element : ls) {
                if (iterable_element.getDatelearn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        .equals(weekDays.get(i))) {
                    sc.add(iterable_element);
                    check = true;
                    break;
                }
            }
            if (check == false) {
                sc.add(null);
            }
        }
        return sc;
    }

    public List<LocalDate> weekendGV(LocalDate localDate, Long id) {
        List<LocalDate> weekDays = new ArrayList<>();
        Eclass cl = cs.findByLgid(id);
        List<Schedule> ls = sr.findByIdClass(cl.getId());

        int size = ls.size() - 1;
        Date st = ls.get(0).getDatelearn();
        Date en = ls.get(size).getDatelearn();

        LocalDate nw = LocalDate.now();
        LocalDate stt = st.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate enn = en.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (nw.isAfter(stt) && nw.isBefore(enn)) {

            LocalDate startOfWeek = nw.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = nw.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            while (!startOfWeek.isAfter(endOfWeek)) {

                weekDays.add(startOfWeek);
                startOfWeek = startOfWeek.plusDays(1);
            }
            return weekDays;
        }
        return weekDays;
    }

    @Override
    public Schedule findById(Long id) {
        return sr.findById(id).get();
    }

    @Override
    public Schedule updateNotification(Long id, boolean isNotification) {
        Schedule scheduleUpdate = sr.getReferenceById(id);
        scheduleUpdate.setNotification(isNotification);

        return sr.save(scheduleUpdate);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = sr.findByIdSchedule(id);

        sr.delete(schedule);

    }

}
