package com.prepsync.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeakAreaResponse {
	
	private String subjectName;
	private String topicName;
	private String reason;

}
