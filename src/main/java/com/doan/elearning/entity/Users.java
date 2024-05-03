package com.doan.elearning.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userss", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;
    private String lgid;
    private String username;
    private String password;
    private String address;
    private String phone;
    private String email;
    private Long idClass;
    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "userss", cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    @JsonBackReference
    @OneToMany(mappedBy = "userss", cascade = CascadeType.ALL)
    private List<Result> results;
    @JsonBackReference
    @OneToMany(mappedBy = "userss", cascade = CascadeType.ALL)
    private List<Attendances> attendances;
    @JsonBackReference
    @OneToMany(mappedBy = "userss", cascade = CascadeType.ALL)
    private List<Forum> forum;
    @JsonBackReference
    @OneToMany(mappedBy = "userss", cascade = CascadeType.ALL)
    private List<Comment> comment;
}
