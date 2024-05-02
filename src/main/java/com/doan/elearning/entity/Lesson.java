package com.doan.elearning.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;
    private String name;
    private String document;
    private String link;
    private String note;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eclass_id", referencedColumnName = "eclass_id")
    private Eclass eclass;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private Users userss;
    @JsonBackReference
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Attendances> attendances;
    @OneToOne
    @JoinColumn(name="schedule_id")
private Schedule schedule;

    // Getter for userss
    public Long getUsersId() {
        return userss != null ? userss.getId() : null;
    }

    // Getter for eclass
    public Long getEclassId() {
        return eclass != null ? eclass.getId() : null;
    }

}
