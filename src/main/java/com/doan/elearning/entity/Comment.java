package com.doan.elearning.entity;

import java.util.Date;
import java.util.List;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendances_id")
    private Long id;
    private String content;
    private Date commentDate;
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    private Users userss;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "forum_id", referencedColumnName = "forum_id")
    private Forum forum;
}
