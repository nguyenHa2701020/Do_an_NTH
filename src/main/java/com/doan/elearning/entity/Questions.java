package com.doan.elearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questions_id")
    private Long id;
    private String type;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level_id", referencedColumnName = "level_id")
    private Level level;
//


}
