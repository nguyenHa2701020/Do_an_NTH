package com.doan.elearning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.entity.Users;
import com.doan.elearning.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Users>> getAll() {
        List<Users> listUser = userService.findALl();
        return ResponseEntity.ok(listUser);
    }
}
