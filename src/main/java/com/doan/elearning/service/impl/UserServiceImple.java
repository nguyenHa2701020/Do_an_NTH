package com.doan.elearning.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.UserDto;
import com.doan.elearning.entity.Users;
import com.doan.elearning.repository.RoleRepository;
import com.doan.elearning.repository.UsersRepository;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService{
    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public Users save(UserDto userDto) {
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setLgid(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setRoles(Arrays.asList(roleRepository.findByName(userDto.getRole())));
        user.setIdClass(userDto.getIdClass());
        return userRepository.save(user);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Users> findALl() {
        return userRepository.findALl();
    }

    @Override
    public Users findByLgid(String idlg) {
        return userRepository.findByLgid(idlg);
    }
}
