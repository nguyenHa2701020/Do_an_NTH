package com.doan.elearning.service;

import java.util.List;

import com.doan.elearning.dto.RoleDto;
import com.doan.elearning.entity.Role;

public interface RoleService {
    Role save(RoleDto roleDto);

    List<Role> findALl();
}
