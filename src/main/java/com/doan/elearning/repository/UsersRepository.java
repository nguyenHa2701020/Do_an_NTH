package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.doan.elearning.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    @Query("select o from Users o where o.lgid = ?1")
    Users findByLgid(String lgid);

    @Query("select p from Users p")
    List<Users> findALl();

    @Query("select o from Users o where o.id = ?1")
    Users findByid(Long id);

    @Query("select o from Users o where o.idClass = ?1")
    List<Users> findByIdClass(Long idClass);
}
