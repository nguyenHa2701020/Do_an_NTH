package com.doan.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.elearning.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Query("select p from Role p")
    List<Role> findALl();
}
// khi thêm tbao vào buổi học thì buổi học sẽ hiển thị nút chuông tbao

//khi học viên bấm vào nút chuông tbao thì sẽ hiển thị ra tbao tương ứng của buổi học đấy