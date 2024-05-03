package com.doan.elearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long idPK;
    @Size(min = 3, max = 10, message = "Id contains 3-10 characters")
    private String id;
    @Size(min = 3, max = 50, message = "User name contains 3-50 characters")
    private String username;

    private String address;


    @Pattern(regexp = "^0[0-9]{9,10}$", message = "Phone number must start with 0 and have a length of 10-11 characters")
    private String phone;
    @Email(message = "Email không hợp lệ")
private String email;
    private String role;
    @Size(min = 6, message = "Password must contains min 6 characters")
    private String password;
    private String repeatPassword;

    private Long idClass;
}
