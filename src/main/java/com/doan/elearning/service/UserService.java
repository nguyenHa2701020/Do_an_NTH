package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.UserDto;
import com.doan.elearning.entity.Users;

public interface UserService{
 Users save(UserDto userDto);

 Users findByUsername(String username);

 Users findByLgid(String idlg);

 List<Users> findALl();
//  List<Users> findGV();
}