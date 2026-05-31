package com.prepsync.dto;

import com.prepsync.enums.ConfidenceLevel;
import com.prepsync.enums.DifficultyLevel;
import com.prepsync.enums.TopicStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TopicRequest {
	@NotNull(message = "Subject id is required")
	private Long subjectId;
	
	@NotBlank(message="topic name is required")
	private String topicName;
	
	private TopicStatus status;
	private DifficultyLevel difficulty;
	private ConfidenceLevel confidenceLevel;
	private String notes;

}
