package com.doan.elearning.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "eclass", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Eclass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eclass_id")
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date start;
    // @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", referencedColumnName = "level_id")
    private Level level;

    private Long idGV;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "eclass", cascade = CascadeType.ALL)
    private List<Lesson> lessons;
    @JsonBackReference
    @OneToMany(mappedBy = "eclass", cascade = CascadeType.ALL)
    private List<Exam> exam;
    @JsonBackReference
    @OneToMany(mappedBy = "eclass", cascade = CascadeType.ALL)
    private List<Schedule> schedule;
    @OneToMany(mappedBy = "eclass", cascade = CascadeType.ALL)
    private List<Forum> forum;
}
