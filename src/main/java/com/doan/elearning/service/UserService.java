package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.UserDto;
import com.doan.elearning.entity.Users;

public interface UserService {
    Users save(UserDto userDto);

    Users findByUsername(String username);

    Users findByLgid(String idlg);

    Users findByid(Long id);

    List<Users> findByIdClass(Long id);

    Users updatUsers(UserDto userDto);

    List<Users> findALl();

    Users saveAdmin();

    void delete(Long id);

    Users changePass(Users users);
}