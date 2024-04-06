package com.doan.elearning.dto;

import com.doan.elearning.entity.Questions;
import com.doan.elearning.entity.Topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDetailDto {
    private Questions questions;

    private Topic topic;

    private int numRead;
    private int numListen;
    private int numWrite;
}
