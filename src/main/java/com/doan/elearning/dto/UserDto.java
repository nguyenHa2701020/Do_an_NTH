package com.doan.elearning.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Size(min = 3, max = 10, message = "Id contains 3-10 characters")
    private String id;
     @Size(min = 3, max = 10, message = "User name contains 3-10 characters")
    private String username;
    @Size(min = 3, max = 10, message = "Address contains 3-10 characters")
    private String address;
    @Size(min= 10, max = 11, message = "Phone contains 10- 11 characters")
    private String phone;
  
    private String Role;
    private String password;
    private String repeatPassword;

    private Long idClass;
}
