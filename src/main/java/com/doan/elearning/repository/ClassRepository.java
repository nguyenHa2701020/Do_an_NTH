package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Eclass;


@Repository
public interface ClassRepository extends JpaRepository<Eclass, Long> {
    @Query("select p from Eclass p")
    List<Eclass> findAll();

    @Query("select o from Eclass o where o.id = ?1")
    Eclass findByLgid(Long id);

    @Query("select o from Eclass o where o.idGV = ?1")
    List<Eclass> findByidGV(Long id);

    @Query(value = "update Eclass set name = ?1 where id=?2")
    Eclass update(String name, Long id);
}
