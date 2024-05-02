package com.doan.elearning.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doan.elearning.dto.UserDto;

import com.doan.elearning.entity.Users;
import com.doan.elearning.repository.RoleRepository;
import com.doan.elearning.repository.UsersRepository;
import com.doan.elearning.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService {
    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Users save(UserDto userDto) {
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
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

    @Override
    public Users saveAdmin() {
        Users user = new Users();
        user.setUsername("Admin");
        user.setAddress("Admin");
        user.setPhone("098765432");
        user.setLgid("Admin");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        return userRepository.save(user);
    }

    @Override
    public Users findByid(Long id) {
        return userRepository.findByid(id);
    }

    @Override
    public void delete(Long id) {
        // Users user = userRepository.getReferenceById(id);
        userRepository.deleteById(id);
    }

    @Override
    public Users updatUsers(UserDto userDto) {
        Users userUpdate = userRepository.getReferenceById(userDto.getIdPK());
userUpdate.setUsername(userDto.getUsername());
        userUpdate.setPhone(userDto.getPhone());
        userUpdate.setAddress(userDto.getAddress());
        userUpdate.setEmail(userDto.getEmail());

        return userRepository.save(userUpdate);
    }

    @Override
    public Users changePass(Users users) {
        Users customer = userRepository.findByUsername(users.getUsername());
        customer.setPassword(users.getPassword());
        return userRepository.save(customer);
    }

    @Override
    public List<Users> findByIdClass(Long id) {
        return userRepository.findByIdClass(id);
    }
}
