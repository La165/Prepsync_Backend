package com.prepsync.dto;

import java.time.LocalDate;

import com.prepsync.enums.DifficultyLevel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectRequest {
	
	@NotBlank(message="Subject name is required")
	private String name;

	private boolean completed;
	private DifficultyLevel priority;
	private LocalDate dueDate;
}
