package com.doan.elearning.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.doan.elearning.dto.RoleDto;
import com.doan.elearning.entity.Role;
import com.doan.elearning.repository.RoleRepository;
import com.doan.elearning.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImple implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findALl() {
        return roleRepository.findALl();
    }

    @Override
    public Role save(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return roleRepository.save(role);
    }

}
