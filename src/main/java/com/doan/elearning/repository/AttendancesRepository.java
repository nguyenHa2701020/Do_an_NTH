package com.doan.elearning.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Attendances;
@Repository
public interface AttendancesRepository extends JpaRepository<Attendances, Long>{
    @Query(value = " select o from Attendances o where o.userss.id =?1 and o.lesson.id=?2")
   Attendances findByUserAndLesson(Long idUser, Long idLesson);
    
} 
    

