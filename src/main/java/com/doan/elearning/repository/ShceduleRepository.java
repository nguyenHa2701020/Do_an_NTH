package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.doan.elearning.entity.Schedule;

@Repository
public interface ShceduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select p from Schedule p")
    List<Schedule> findAll();

    @Query("select o from Schedule o where o.eclass.id = ?1")
    List<Schedule> findByIdClass(Long eclass_id);
 @Query("select o from Schedule o where o.id = ?1")
Schedule findByIdSchedule(Long id);
}
