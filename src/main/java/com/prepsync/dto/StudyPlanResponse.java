package com.prepsync.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudyPlanResponse {
	
	private LocalDate date;
	private List<String> tasks;

}
