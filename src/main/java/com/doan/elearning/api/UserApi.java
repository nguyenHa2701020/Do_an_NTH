package com.doan.elearning.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.elearning.dto.ResponseObject;
import com.doan.elearning.dto.TopicDto;
import com.doan.elearning.dto.UserDto;
import com.doan.elearning.entity.Topic;
import com.doan.elearning.entity.Users;
import com.doan.elearning.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAll() {
        List<Users> listUser = userService.findALl();
        return ResponseEntity.ok(listUser);
    }

    @PostMapping("/save")
    public ResponseEntity<Users> saveUsers(@ModelAttribute UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode("123456"));
        Users users = userService.save(userDto);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) {
        Users users = userService.findByid(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@ModelAttribute UserDto userDto) {
        userService.updatUsers(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saveUS")
    public ResponseEntity<ResponseObject> saveUS(@RequestBody  @Valid UserDto userDto, BindingResult result) {
        ResponseObject responseObject = new ResponseObject();
        if (result.hasErrors()) {
            setErrorsForResponseObject(result, responseObject);
        } else {
            userDto.setPassword(passwordEncoder.encode("123456"));
            responseObject.setStatus("success");

            userService.save(userDto);
        }

        return ResponseEntity.ok(responseObject);
    }

    public void setErrorsForResponseObject(BindingResult result, ResponseObject object) {

        Map<String, String> errors = result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        object.setErrorMessages(errors);
        object.setStatus("fail");

        List<String> keys = new ArrayList<String>(errors.keySet());
        for (String key : keys) {
            System.out.println(key + ": " + errors.get(key));
        }

        errors = null;
    }

}
