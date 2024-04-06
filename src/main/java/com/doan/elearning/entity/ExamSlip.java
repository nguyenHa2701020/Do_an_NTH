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
@Table(name = "examslip")
public class ExamSlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examslip_id")
    private Long id;
    private String answer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topicDetail_id", referencedColumnName = "topicDetail_id")
    private TopicDetail topicDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private Users users;


}
