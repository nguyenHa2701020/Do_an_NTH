package com.doan.elearning.dto;

import com.doan.elearning.entity.TopicDetail;
import com.doan.elearning.entity.Users;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSlipDto {
    private String answer;

    private TopicDetail topicDetail;

    private Users users;
}
