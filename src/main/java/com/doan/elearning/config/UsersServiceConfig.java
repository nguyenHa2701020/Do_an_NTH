package com.doan.elearning.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.doan.elearning.entity.Users;
import com.doan.elearning.repository.UsersRepository;

import java.util.stream.Collectors;

public class UsersServiceConfig implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Users user = userRepository.findByUsername(username);
        Users user = userRepository.findByLgid(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find username");
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}
