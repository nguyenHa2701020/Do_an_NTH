package com.doan.elearning.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private Object data;
	private Map<String, String> errorMessages = null ;
	private String status;
}
